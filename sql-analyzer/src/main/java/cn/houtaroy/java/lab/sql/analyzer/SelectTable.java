package cn.houtaroy.java.lab.sql.analyzer;

import com.alibaba.druid.sql.ast.statement.SQLExprTableSource;
import lombok.Data;

/**
 * @author Houtaroy
 */
@Data
public class SelectTable {

  protected String name;
  protected String alias;

  public SelectTable(SQLExprTableSource expr) {
    this.name = expr.getTableName();
    this.alias = expr.getAlias();
  }
}
