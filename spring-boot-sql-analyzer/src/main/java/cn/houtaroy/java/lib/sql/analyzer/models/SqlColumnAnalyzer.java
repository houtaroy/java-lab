package cn.houtaroy.java.lib.sql.analyzer.models;

import java.util.List;

/**
 * @author Houtaroy
 */
public interface SqlColumnAnalyzer {

  /**
   * 分析SQL语句列
   *
   * @param sql sql语句
   * @return 列列表
   */
  List<? extends Column> analyze(String sql);
}
