package cn.houtaroy.java.lib.sql.analyzer.models;

import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.scripting.xmltags.DynamicSqlSource;
import org.apache.ibatis.scripting.xmltags.TextSqlNode;
import org.apache.ibatis.session.Configuration;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Houtaroy
 */
public class SimpleSqlParameterAnalyzer implements SqlParameterAnalyzer {

  private static final Configuration CONFIGURATION = new Configuration();

  @Override
  public List<SimpleParameter> analyze(String sql) {
    return new DynamicSqlSource(CONFIGURATION, new TextSqlNode(sql))
      .getBoundSql(null).getParameterMappings().stream().map(ParameterMapping::getProperty)
      .map(SimpleParameter::new).collect(Collectors.toList());
  }
}
