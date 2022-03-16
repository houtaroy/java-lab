package cn.houtaroy.java.lib.sql.analyzer.models;

import com.alibaba.druid.wall.Violation;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Houtaroy
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class DruidSqlAnalysis extends SimpleSqlAnalysis {

  private List<Violation> violations;
}
