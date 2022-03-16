package cn.houtaroy.java.lib.sql.analyzer.apis;

import cn.houtaroy.java.lib.sql.analyzer.models.SqlAnalysis;
import cn.houtaroy.java.lib.sql.analyzer.models.SqlAnalyzer;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Houtaroy
 */
@RestController
@RequestMapping("/parse")
@RequiredArgsConstructor
public class ParserApi {

  private final SqlAnalyzer analyzer;

  @GetMapping("error")
  public SqlAnalysis error() {
    String error = "select t.* from t_user t where id = #{id} and 1=1";
    return analyzer.analyze(error);
  }

  @GetMapping("simple")
  public SqlAnalysis simple() {
    String simple = "select t.name as name, t.gender as sex from t_user t where t.name like '%#{name}%'";
    return analyzer.analyze(simple);
  }

  @GetMapping("mixed")
  public SqlAnalysis mixed() {
    String mixed = "select t.*, d.name as departmentName, c.name as createUserName, o.* from t_user t left join t_department d on t.department_id = d.id left join t_organization o on t.organization_id = o.id left join t_user c on t.create_user_id = c.id where t.name like '%#{name}%' and gender = #{gender}";
    return analyzer.analyze(mixed);
  }
}
