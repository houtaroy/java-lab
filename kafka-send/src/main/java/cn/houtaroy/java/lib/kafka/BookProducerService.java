package cn.houtaroy.java.lib.kafka;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.concurrent.ExecutionException;

/**
 * @author Houtaroy
 */
@AllArgsConstructor
@Component
@Slf4j
public class BookProducerService {
  private final KafkaTemplate<String, Object> kafkaTemplate;

  public void sendMessage(String topic, Object o) {
    kafkaTemplate.send(topic, o);
  }

  /**
   * 同步发送消息
   *
   * @param topic topic
   * @param o     消息
   */
  public void syncSendMessage(String topic, Object o) {
    try {
      success(kafkaTemplate.send(topic, o).get());
    } catch (InterruptedException | ExecutionException e) {
      error(e);
    }
  }

  /**
   * 异步发送消息
   *
   * @param topic topic
   * @param o     消息
   */
  public void asyncSendMessage(String topic, Object o) {
    kafkaTemplate.send(topic, o).addCallback(this::success, this::error);
  }

  protected void success(SendResult<String, Object> result) {
    Optional.ofNullable(result).ifPresent(r -> LOGGER.info(
      "生产者成功发送消息到topic:{} partition:{}",
      r.getRecordMetadata().topic(),
      r.getRecordMetadata().partition()
    ));
  }

  public void error(Throwable ex) {
    LOGGER.error("生产者发送消息失败", ex);
  }
}
