package cn.houtaroy.java.lib.sql.analyzer.repositories;

import cn.houtaroy.java.lib.sql.analyzer.models.ColumnDefinition;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author Houtaroy
 */
public interface SchemaRepository {

  /**
   * 根据参数查询列定义
   *
   * @param parameters 查询参数
   * @return 列定义列表
   */
  List<? extends ColumnDefinition> listColumnDefinitions(@Param("parameters") Map<String, Object> parameters);
}
