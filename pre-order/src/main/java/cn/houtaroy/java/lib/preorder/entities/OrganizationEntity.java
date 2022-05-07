package cn.houtaroy.java.lib.preorder.entities;

import lombok.Data;

/**
 * @author Houtaroy
 */
@Data
public class OrganizationEntity {
  private Integer id;
  private String code;
  private String name;
  private Integer leftValue;
  private Integer rightValue;
  private Integer level;
}
