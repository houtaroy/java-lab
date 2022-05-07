package cn.houtaroy.java.lib.kafka;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Houtaroy
 */
@AllArgsConstructor
@Data
@NoArgsConstructor
public class Book {
  private Long id;
  private String name;
}
