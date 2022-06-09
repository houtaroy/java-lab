package cn.houtaroy.java.lab.sql.analyzer;

import com.alibaba.druid.DbType;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Houtaroy
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class NoSuchWallProviderException extends Exception {

  private final DbType dbType;

  public NoSuchWallProviderException(DbType dbType) {
    super(String.format("no such WallProvider for database %s, you can add one by yourself", dbType.name()));
    this.dbType = dbType;
  }
}
