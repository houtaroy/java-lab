package cn.houtaroy.java.lib;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Houtaroy
 */
@SpringBootApplication
@MapperScan(basePackages = "cn.houtaroy.java.lib.mapper")
public class ShardingDatasourceTwoApplication {

}
