package cn.houtaroy.java.lib.sql.analyzer.models;

import java.util.List;

/**
 * @author Houtaroy
 */
public interface SqlParameterAnalyzer {

  /**
   * 分析SQL语句参数
   *
   * @param sql sql语句
   * @return 参数列表
   */
  List<? extends Parameter> analyze(String sql);
}
