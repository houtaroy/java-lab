package cn.houtaroy.java.lib.sql.analyzer.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Houtaroy
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SimpleSqlAnalysis implements SqlAnalysis {

  private List<SimpleColumn> columns;
  private List<SimpleParameter> parameters;
}
