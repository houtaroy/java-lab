package cn.houtaroy.java.lab.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.config.TopicConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.support.converter.RecordMessageConverter;
import org.springframework.kafka.support.converter.StringJsonMessageConverter;

/**
 * @author Houtaroy
 */
@Configuration
public class KafkaConfig {
  @Value("${kafka.topic.my-topic}")
  String myTopic;
  @Value("${kafka.topic.my-topic2}")
  String myTopic2;

  /**
   * JSON消息转换器
   */
  @Bean
  public RecordMessageConverter jsonConverter() {
    return new StringJsonMessageConverter();
  }

  /**
   * 通过注入一个 NewTopic 类型的 Bean 来创建 topic，如果 topic 已存在，则会忽略。
   */
  @Bean
  public NewTopic myTopic() {
    return TopicBuilder
      .name(myTopic)
      .partitions(2)
      .replicas(3)
      .config(TopicConfig.MIN_IN_SYNC_REPLICAS_CONFIG, "2")
      .config(TopicConfig.UNCLEAN_LEADER_ELECTION_ENABLE_CONFIG, "true")
      .build();
  }

  @Bean
  public NewTopic myTopic2() {
    return new NewTopic(myTopic2, 1, (short) 1);
  }
}
