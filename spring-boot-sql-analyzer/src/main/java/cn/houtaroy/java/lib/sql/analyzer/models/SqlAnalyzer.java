package cn.houtaroy.java.lib.sql.analyzer.models;

/**
 * @author Houtaroy
 */
public interface SqlAnalyzer {

  /**
   * 分析SQL语句
   *
   * @param sql sql
   * @return 分析结果
   */
  SqlAnalysis analyze(String sql);
}
