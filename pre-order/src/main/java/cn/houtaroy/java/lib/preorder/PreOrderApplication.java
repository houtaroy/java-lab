package cn.houtaroy.java.lib.preorder;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Houtaroy
 */
@SpringBootApplication
@MapperScan(basePackages = "cn.houtaroy.java.lib.preorder.repositories")
public class PreOrderApplication {
}
