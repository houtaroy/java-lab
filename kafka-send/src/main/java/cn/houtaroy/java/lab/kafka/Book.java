package cn.houtaroy.java.lab.kafka;

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
