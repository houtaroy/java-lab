package cn.houtaroy.java.lib.sql.analyzer.models;

import com.alibaba.druid.sql.ast.statement.SQLSelectStatement;
import com.alibaba.druid.sql.visitor.SchemaStatVisitor;
import com.alibaba.druid.stat.TableStat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Houtaroy
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class DruidSqlAnalyzeVisitor extends SchemaStatVisitor {

  protected List<String> columnAlias;
  protected List<SimpleColumn> selectColumns;

  @Override
  public void endVisit(SQLSelectStatement x) {
    super.endVisit(x);
    columnAlias = x.computeSelecteListAlias();
    selectColumns = getColumns().stream().filter(TableStat.Column::isSelect).map(SimpleColumn::new)
      .collect(Collectors.toList());
  }

  /**
   * 计算查询列别名映射Map
   *
   * @return 查询列别名映射Map, key为查询列, value为别名
   */
  public Map<SimpleColumn, String> computeSelectColumnAliaMap() {
    Map<SimpleColumn, String> result = new HashMap<>(columnAlias.size());
    List<SimpleColumn> aliaColumns = getAliaColumns();
    for (int i = 0; i < columnAlias.size(); i++) {
      result.put(aliaColumns.get(i), columnAlias.get(i));
    }
    return result;
  }

  /**
   * 计算查询的所有表名
   *
   * @return 表名列表
   */
  public Set<String> computeSelectTableNames() {
    return selectColumns.stream().map(SimpleColumn::getTableName).collect(Collectors.toSet());
  }

  private List<SimpleColumn> getAliaColumns() {
    return selectColumns.stream().filter(column -> !column.isSelectAll()).collect(Collectors.toList());
  }
}
