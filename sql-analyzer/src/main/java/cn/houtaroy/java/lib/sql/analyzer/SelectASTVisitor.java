package cn.houtaroy.java.lib.sql.analyzer;

import com.alibaba.druid.sql.ast.expr.SQLAllColumnExpr;
import com.alibaba.druid.sql.ast.expr.SQLCharExpr;
import com.alibaba.druid.sql.ast.expr.SQLIdentifierExpr;
import com.alibaba.druid.sql.ast.expr.SQLMethodInvokeExpr;
import com.alibaba.druid.sql.ast.expr.SQLPropertyExpr;
import com.alibaba.druid.sql.ast.expr.SQLVariantRefExpr;
import com.alibaba.druid.sql.ast.statement.SQLExprTableSource;
import com.alibaba.druid.sql.ast.statement.SQLSelectItem;
import com.alibaba.druid.sql.ast.statement.SQLSelectQueryBlock;
import com.alibaba.druid.sql.visitor.SQLASTVisitor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 查询语句访问者
 * 只适用于select语句, 可以获取查询列名与查询参数
 * 查询参数书写格式为#{[a-zA-z]}
 *
 * @author Houtaroy
 */
@Data
@SuppressWarnings("PMD")
public class SelectASTVisitor implements SQLASTVisitor {

  public static final Pattern PARAMETER_PATTERN = Pattern.compile("#\\{[a-zA-z]*}");
  private static final int PARAMETER_START_INDEX = 2;

  protected List<SQLSelectItem> selectItems = new ArrayList<>();
  protected List<SelectTable> selectTables = new ArrayList<>();
  protected List<SelectColumn> selectColumns = new ArrayList<>();
  protected List<String> parameters = new ArrayList<>();

  @Override
  public void endVisit(SQLSelectQueryBlock x) {
    computeSelectColumns();
  }

  @Override
  public boolean visit(SQLExprTableSource x) {
    selectTables.add(new SelectTable(x));
    return false;
  }

  @Override
  public boolean visit(SQLCharExpr x) {
    computeParameter(x.toString());
    return false;
  }

  @Override
  public boolean visit(SQLSelectItem x) {
    selectItems.add(x);
    return false;
  }

  @Override
  public boolean visit(SQLVariantRefExpr x) {
    computeParameter(x.getName());
    return false;
  }

  /**
   * 访问查询参数表达式, 匹配查询参数
   *
   * @param expr 查询参数表达式
   */
  protected void computeParameter(String expr) {
    Matcher matcher = PARAMETER_PATTERN.matcher(expr);
    if (matcher.find()) {
      String match = matcher.group();
      parameters.add(match.substring(PARAMETER_START_INDEX, match.length() - 1));
    }
  }

  /**
   * 计算查询列
   */
  protected void computeSelectColumns() {
    selectItems.forEach(item -> {
      String alias = item.getAlias();
      if (item.getExpr() instanceof SQLIdentifierExpr) {
        SQLIdentifierExpr expr = (SQLIdentifierExpr) item.getExpr();
        selectColumns.add(new SelectColumn(selectTables.get(0).getName(), expr.getName(), alias));
      } else if (item.getExpr() instanceof SQLAllColumnExpr) {
        SQLAllColumnExpr expr = (SQLAllColumnExpr) item.getExpr();
        selectColumns.add(new SelectColumn(selectTables.get(0).getName(), expr.toString(), alias));
      } else if (item.getExpr() instanceof SQLMethodInvokeExpr) {
        SQLMethodInvokeExpr expr = (SQLMethodInvokeExpr) item.getExpr();
        selectColumns.add(new SelectColumn(null, expr.toString(), alias));
      } else if (item.getExpr() instanceof SQLPropertyExpr) {
        SQLPropertyExpr expr = (SQLPropertyExpr) item.getExpr();
        selectColumns.add(new SelectColumn(getSelectTableNameByAlias(expr.getOwnerName()), expr.getName(),
          item.getAlias()));
      }
    });
  }

  /**
   * 根据查询表别名获取查询表名
   * getSelectTableNameByAlias("t") -> "t_user" or null
   *
   * @param alias 查询表别名
   * @return 查询表名
   */
  protected String getSelectTableNameByAlias(String alias) {
    return getSelectTableByAlias(alias).map(SelectTable::getName).orElse(null);
  }

  /**
   * 根据查询表别名获取查询表
   *
   * @param alias 查询表别名
   * @return 查询表
   */
  protected Optional<SelectTable> getSelectTableByAlias(String alias) {
    return selectTables.stream().filter(table -> alias.equals(table.getAlias())).findFirst();
  }
}
