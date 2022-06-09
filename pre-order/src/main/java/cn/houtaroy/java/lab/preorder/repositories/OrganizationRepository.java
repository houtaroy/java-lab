package cn.houtaroy.java.lab.preorder.repositories;

import cn.houtaroy.java.lab.preorder.entities.OrganizationEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author Houtaroy
 */
@Mapper
public interface OrganizationRepository {
  /**
   * 查询组织机构
   *
   * @param parameters 查询参数
   * @return 组织机构列表
   */
  List<OrganizationEntity> find(@Param("parameters") Map<String, Object> parameters);
}
