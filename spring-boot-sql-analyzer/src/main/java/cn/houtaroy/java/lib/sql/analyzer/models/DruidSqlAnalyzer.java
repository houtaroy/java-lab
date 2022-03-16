package cn.houtaroy.java.lib.sql.analyzer.models;

import com.alibaba.druid.wall.Violation;
import com.alibaba.druid.wall.WallProvider;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

/**
 * @author Houtaroy
 */
@Data
@RequiredArgsConstructor
public class DruidSqlAnalyzer implements SqlAnalyzer {

  private final DruidSqlColumnAnalyzer columnAnalyzer;
  private final SimpleSqlParameterAnalyzer parameterAnalyzer;
  private final WallProvider provider;

  @Override
  public DruidSqlAnalysis analyze(String sql) {
    DruidSqlAnalysis result = new DruidSqlAnalysis();
    List<Violation> violations = provider.check(sql).getViolations();
    if (violations.isEmpty()) {
      result.setColumns(columnAnalyzer.analyze(sql));
      result.setParameters(parameterAnalyzer.analyze(sql));
    }
    result.setViolations(violations);
    return result;
  }
}
