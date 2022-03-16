package cn.houtaroy.java.lib.sql.analyzer.models;

/**
 * @author Houtaroy
 */
public interface ColumnDefinition {

  /**
   * 获取表名
   *
   * @return 表名
   */
  String getTableName();

  /**
   * 获取列名
   *
   * @return 列名
   */
  String getName();

  /**
   * 获取数据类型
   *
   * @return 数据类型
   */
  String getDataType();

  /**
   * 获取备注
   *
   * @return 备注
   */
  String getComment();
}
