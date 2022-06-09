package cn.houtaroy.java.lab.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author Houtaroy
 */
@Data
@TableName(value = "orders")
public class OrderDO {
  private Long id;
  private Integer userId;
}
