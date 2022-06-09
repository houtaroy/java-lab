package cn.houtaroy.java.lab.preorder;

import cn.houtaroy.java.lab.preorder.services.OrganizationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.HashMap;

/**
 * @author Houtaroy
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = PreOrderApplication.class)
public class OrganizationServiceTest {
  @Autowired
  private OrganizationService service;

  @Test
  public void testSelectById() {
    System.out.println(service.list(new HashMap<>()));
  }
}
