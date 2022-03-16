package cn.houtaroy.java.lib.sql.analyzer.config;

import cn.houtaroy.java.lib.sql.analyzer.models.DruidSqlAnalyzeVisitor;
import cn.houtaroy.java.lib.sql.analyzer.models.DruidSqlAnalyzer;
import cn.houtaroy.java.lib.sql.analyzer.models.DruidSqlColumnAnalyzer;
import cn.houtaroy.java.lib.sql.analyzer.models.SimpleSqlParameterAnalyzer;
import cn.houtaroy.java.lib.sql.analyzer.models.SqlAnalyzer;
import cn.houtaroy.java.lib.sql.analyzer.repositories.MySqlSchemaRepository;
import com.alibaba.druid.DbType;
import com.alibaba.druid.wall.spi.MySqlWallProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Houtaroy
 */
@Configuration
public class BeanConfig {

  @Bean
  public SqlAnalyzer sqlAnalyzer(MySqlSchemaRepository schemaRepository) {
    return new DruidSqlAnalyzer(new DruidSqlColumnAnalyzer(DbType.mysql, DruidSqlAnalyzeVisitor::new, schemaRepository),
      new SimpleSqlParameterAnalyzer(), new MySqlWallProvider());
  }
}
