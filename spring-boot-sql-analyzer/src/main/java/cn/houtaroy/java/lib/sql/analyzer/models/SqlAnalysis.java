package cn.houtaroy.java.lib.sql.analyzer.models;

import java.util.List;

/**
 * @author Houtaroy
 */
public interface SqlAnalysis {

  /**
   * 获取数据库列列表
   *
   * @return 数据库列列表
   */
  List<? extends Column> getColumns();

  /**
   * 获取参数列表
   *
   * @return 参数列表
   */
  List<? extends Parameter> getParameters();
}
