package cn.houtaroy.java.lib.sql.analyzer.models;

import com.alibaba.druid.stat.TableStat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Houtaroy
 */
@Data
@NoArgsConstructor
public class SimpleColumn implements Column {

  protected String tableName;
  protected String name;
  protected String alia;
  protected String dataType;
  protected String comment;

  public SimpleColumn(TableStat.Column column) {
    tableName = column.getTable();
    name = column.getName();
  }

  public SimpleColumn(ColumnDefinition definition) {
    tableName = definition.getTableName();
    name = definition.getName();
    dataType = definition.getDataType();
    comment = definition.getComment();
  }

  @JsonIgnore
  public boolean isSelectAll() {
    return "*".equals(name);
  }

  /**
   * 是否为数据库列定义
   *
   * @param definition 数据库列定义
   * @return true 是 false 否
   */
  @JsonIgnore
  public boolean isDefinition(ColumnDefinition definition) {
    return tableName.equalsIgnoreCase(definition.getTableName()) && name.equalsIgnoreCase(definition.getName());
  }
}
