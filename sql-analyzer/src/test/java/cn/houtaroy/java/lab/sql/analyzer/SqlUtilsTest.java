package cn.houtaroy.java.lab.sql.analyzer;

import com.alibaba.druid.DbType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Houtaroy
 */
public class SqlUtilsTest {

  /**
   * SQL编译测试
   */
  @Test
  void compile() {
    String sql = "select * from t_user where id = #{id} and name like '#{name}%'";
    Map<String, Object> parameters = new HashMap<>();
    parameters.put("id", 1);
    parameters.put("name", "admin");
    Assertions.assertEquals(SqlUtils.compile(sql, parameters),
      "select * from t_user where id = 1 and name like 'admin%'");
  }

  /**
   * 检查SQL语句
   * 包含正确/错误/语法异常/存在SQL注入
   *
   * @throws NoSuchWallProviderException 无对应数据库的WallProvider
   */
  @Test
  void check() throws NoSuchWallProviderException {
    String success = "select * from t_user where id = 1";
    String error = "select oh ye where ";
    String injection = String.format("%s and 1 = 1", success);
    Assertions.assertTrue(SqlUtils.check(success, DbType.mysql).getViolations().isEmpty());
    Assertions.assertTrue(SqlUtils.check(error, DbType.mysql).isSyntaxError());
    Assertions.assertFalse(SqlUtils.check(injection, DbType.mysql).getViolations().isEmpty());
    Assertions.assertThrowsExactly(NoSuchWallProviderException.class, () -> SqlUtils.check(injection, DbType.dm),
      "no such WallProvider for database dm, you can add one by yourself");
  }
}
