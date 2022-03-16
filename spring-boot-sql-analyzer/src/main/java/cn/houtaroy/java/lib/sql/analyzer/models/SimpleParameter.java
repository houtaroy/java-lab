package cn.houtaroy.java.lib.sql.analyzer.models;

import lombok.Data;

/**
 * @author Houtaroy
 */
@Data
public class SimpleParameter implements Parameter {

  private String name;

  public SimpleParameter(String name) {
    this.name = name;
  }
}
