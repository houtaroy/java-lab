package cn.houtaroy.java.lib.sql.analyzer;

import com.alibaba.druid.DbType;
import com.alibaba.druid.sql.SQLUtils;
import com.alibaba.druid.sql.ast.SQLStatement;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * @author Houtaroy
 */
public class SelectASTVisitorTableTest {

  /**
   * 简单表名
   */
  @Test
  void simple() {
    String sql = "select * from t_user";
    SQLStatement statement = SQLUtils.parseSingleStatement(sql, DbType.mysql);
    SelectASTVisitor visitor = new SelectASTVisitor();
    statement.accept(visitor);
    List<SelectTable> selectTables = visitor.getSelectTables();
    Assertions.assertEquals(selectTables.size(), 1);
    Assertions.assertEquals(selectTables.get(0).getName(), "t_user");
  }

  /**
   * 有别名的表名
   */
  @Test
  void alias() {
    String sql = "select t.*, d.* from t_user t left join t_department d on t.department_id = d.id";
    SQLStatement statement = SQLUtils.parseSingleStatement(sql, DbType.mysql);
    SelectASTVisitor visitor = new SelectASTVisitor();
    statement.accept(visitor);
    List<SelectTable> selectTables = visitor.getSelectTables();
    Assertions.assertEquals(selectTables.size(), 2);
    Assertions.assertEquals(selectTables.get(0).getName(), "t_user");
    Assertions.assertEquals(selectTables.get(0).getAlias(), "t");
    Assertions.assertEquals(selectTables.get(1).getName(), "t_department");
    Assertions.assertEquals(selectTables.get(1).getAlias(), "d");
  }
}
