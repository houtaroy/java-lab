package cn.houtaroy.java.lib.sql.analyzer.models;

/**
 * @author Houtaroy
 */
public interface Column extends ColumnDefinition {

  /**
   * 获取别名
   *
   * @return 别名
   */
  String getAlia();
}
