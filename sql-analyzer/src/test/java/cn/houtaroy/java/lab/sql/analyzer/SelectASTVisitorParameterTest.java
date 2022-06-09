package cn.houtaroy.java.lab.sql.analyzer;

import com.alibaba.druid.DbType;
import com.alibaba.druid.sql.SQLUtils;
import com.alibaba.druid.sql.ast.SQLStatement;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * 查询SQL访问者查询参数单元测试
 *
 * @author Houtaroy
 */
public class SelectASTVisitorParameterTest {

  /**
   * 简单的查询参数
   */
  @Test
  void simple() {
    String sql = "select * from t_user where id = '#{id}'";
    SQLStatement statement = SQLUtils.parseSingleStatement(sql, DbType.mysql);
    SelectASTVisitor visitor = new SelectASTVisitor();
    statement.accept(visitor);
    Assertions.assertEquals(visitor.getParameters().size(), 1);
    Assertions.assertEquals(visitor.getParameters().get(0), "id");
  }

  /**
   * 多个查询参数, 且包含like
   */
  @Test
  void multiple() {
    String sql = "select * from t_user where id = #{id} and name like '#{name}%'";
    SQLStatement statement = SQLUtils.parseSingleStatement(sql, DbType.mysql);
    SelectASTVisitor visitor = new SelectASTVisitor();
    statement.accept(visitor);
    Assertions.assertEquals(visitor.getParameters().size(), IntConstant.INT_2);
    Assertions.assertEquals(visitor.getParameters().get(0), "id");
    Assertions.assertEquals(visitor.getParameters().get(1), "name");
  }

  /**
   * 分组的查询参数, 且包含in
   */
  @Test
  void group() {
    String sql = "select * from t_user where id = #{id} or (name like '#{name}%' and gender in (#{genderOne}, "
      + "#{genderTwo}))";
    SQLStatement statement = SQLUtils.parseSingleStatement(sql, DbType.mysql);
    SelectASTVisitor visitor = new SelectASTVisitor();
    statement.accept(visitor);
    Assertions.assertEquals(visitor.getParameters().size(), IntConstant.INT_4);
    Assertions.assertEquals(visitor.getParameters().get(0), "id");
    Assertions.assertEquals(visitor.getParameters().get(1), "name");
    Assertions.assertEquals(visitor.getParameters().get(IntConstant.INT_2), "genderOne");
  }
}
