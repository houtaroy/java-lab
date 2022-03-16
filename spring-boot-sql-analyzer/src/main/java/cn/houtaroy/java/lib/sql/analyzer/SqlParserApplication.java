package cn.houtaroy.java.lib.sql.analyzer;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Houtaroy
 */
@SpringBootApplication
public class SqlParserApplication {

    /**
     * main方法
     *
     * @param args 参数
     */
    public static void main(String[] args) {
        SpringApplication.run(SqlParserApplication.class, args);
    }
}
