package cn.houtaroy.java.lib.sql.analyzer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Houtaroy
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SelectColumn {

  protected String table;
  protected String name;
  protected String alias;

  public boolean isSelectAll() {
    return "*".equals(name);
  }
}
