package cn.houtaroy.java.lib.sql.analyzer.repositories;

import cn.houtaroy.java.lib.sql.analyzer.models.SimpleColumn;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author Houtaroy
 */
@Mapper
public interface MySqlSchemaRepository extends SchemaRepository {

  /**
   * 根据参数查询列定义
   *
   * @param parameters 查询参数
   * @return 列定义列表
   */
  @Override
  List<SimpleColumn> listColumnDefinitions(Map<String, Object> parameters);
}
