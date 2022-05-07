package cn.houtaroy.java.lib.kafka;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author Houtaroy
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = KafkaSendApplication.class)
public class KafkaTest {
  private final AtomicLong atomicLong = new AtomicLong();
  @Value("${kafka.topic.my-topic}")
  String myTopic;
  @Value("${kafka.topic.my-topic2}")
  String myTopic2;
  @Autowired
  private BookProducerService producerService;

  @Test
  public void send() {
    producerService.syncSendMessage(myTopic, new Book(atomicLong.addAndGet(1), "第一主题"));
    producerService.asyncSendMessage(myTopic2, new Book(atomicLong.addAndGet(1), "第二主题"));
  }
}
