package cn.houtaroy.java.lib.sql.analyzer;

import com.alibaba.druid.DbType;
import com.alibaba.druid.wall.WallCheckResult;
import com.alibaba.druid.wall.WallProvider;
import com.alibaba.druid.wall.spi.DB2WallProvider;
import com.alibaba.druid.wall.spi.MySqlWallProvider;
import com.alibaba.druid.wall.spi.OracleWallProvider;
import com.alibaba.druid.wall.spi.PGWallProvider;
import com.alibaba.druid.wall.spi.SQLServerWallProvider;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Houtaroy
 */
public class SqlUtils {

  public static final Map<DbType, WallProvider> PROVIDER_MAP;

  static {
    PROVIDER_MAP = new HashMap<>(DbType.values().length);
    PROVIDER_MAP.put(DbType.mysql, new MySqlWallProvider());
    PROVIDER_MAP.put(DbType.oracle, new OracleWallProvider());
    PROVIDER_MAP.put(DbType.sqlserver, new SQLServerWallProvider());
    PROVIDER_MAP.put(DbType.postgresql, new PGWallProvider());
    PROVIDER_MAP.put(DbType.db2, new DB2WallProvider());
  }

  /**
   * 编译SQL
   *
   * @param sql        sql语句
   * @param parameters 查询参数
   * @return 编译结果
   */
  public static String compile(String sql, Map<String, Object> parameters) {
    String result = sql;
    for (String key : parameters.keySet()) {
      result = result.replaceAll(String.format("#\\{%s}", key), parameters.get(key).toString());
    }
    return result;
  }

  /**
   * 检查SQL
   *
   * @param sql    sql语句
   * @param dbType 数据库类型
   * @return 检查结果
   * @throws NoSuchWallProviderException 无对应数据库的WallProvider
   */
  public static WallCheckResult check(String sql, DbType dbType) throws NoSuchWallProviderException {
    WallProvider provider = PROVIDER_MAP.get(dbType);
    if (provider != null) {
      return provider.check(sql);
    }
    throw new NoSuchWallProviderException(dbType);
  }
}
