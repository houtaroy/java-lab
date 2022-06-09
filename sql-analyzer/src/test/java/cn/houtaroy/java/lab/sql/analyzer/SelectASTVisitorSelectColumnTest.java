package cn.houtaroy.java.lab.sql.analyzer;

import com.alibaba.druid.DbType;
import com.alibaba.druid.sql.SQLUtils;
import com.alibaba.druid.sql.ast.SQLStatement;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * 查询SQL访问者查询列单元测试
 *
 * @author Houtaroy
 */
public class SelectASTVisitorSelectColumnTest {

  /**
   * 简单的SQL
   */
  @Test
  void simple() {
    String sql = "select name, gender from t_user";
    SQLStatement statement = SQLUtils.parseSingleStatement(sql, DbType.mysql);
    SelectASTVisitor visitor = new SelectASTVisitor();
    statement.accept(visitor);
    List<SelectColumn> selectColumns = visitor.getSelectColumns();
    Assertions.assertEquals(selectColumns.size(), IntConstant.INT_2);
    Assertions.assertEquals(selectColumns.get(0).getTable(), "t_user");
    Assertions.assertEquals(selectColumns.get(0).getName(), "name");
    Assertions.assertEquals(selectColumns.get(1).getName(), "gender");
  }

  /**
   * 列有别名的SQL
   */
  @Test
  void alias() {
    String sql = "select gender as sex from t_user";
    SQLStatement statement = SQLUtils.parseSingleStatement(sql, DbType.mysql);
    SelectASTVisitor visitor = new SelectASTVisitor();
    statement.accept(visitor);
    Assertions.assertEquals(visitor.getSelectColumns().get(0).getAlias(), "sex");
  }

  /**
   * 表有别名的SQL
   */
  @Test
  void tableAlias() {
    String sql = "select t.name from t_user t";
    SQLStatement statement = SQLUtils.parseSingleStatement(sql, DbType.mysql);
    SelectASTVisitor visitor = new SelectASTVisitor();
    statement.accept(visitor);
    Assertions.assertEquals(visitor.getSelectColumns().get(0).getTable(), "t_user");
  }

  /**
   * 有函数的SQL
   */
  @Test
  void function() {
    String sql = "select concat(t.email, t.name), my_custom_func(t.email, t.name) as my_custom_func from t_user t";
    SQLStatement statement = SQLUtils.parseSingleStatement(sql, DbType.mysql);
    SelectASTVisitor visitor = new SelectASTVisitor();
    statement.accept(visitor);
    Assertions.assertNull(visitor.getSelectColumns().get(0).getTable());
    Assertions.assertEquals(visitor.getSelectColumns().get(0).getName(), "concat(t.email, t.name)");
    Assertions.assertEquals(visitor.getSelectColumns().get(1).getAlias(), "my_custom_func");
  }

  /**
   * 查询全部列的SQL
   */
  @Test
  void all() {
    String sql = "select t.* from t_user t";
    SQLStatement statement = SQLUtils.parseSingleStatement(sql, DbType.mysql);
    SelectASTVisitor visitor = new SelectASTVisitor();
    statement.accept(visitor);
    Assertions.assertEquals(visitor.getSelectColumns().get(0).getName(), "*");
  }
}
