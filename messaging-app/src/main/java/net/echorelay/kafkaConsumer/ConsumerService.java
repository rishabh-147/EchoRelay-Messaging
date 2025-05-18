package net.echorelay.kafkaConsumer;

import java.time.Duration;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.stereotype.Component;

@Component
public class ConsumerService {

	public String recieveMsg(int userId) {
		Properties properties = new Properties();
		String msg = null;

		properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
		properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
		properties.put(ConsumerConfig.GROUP_ID_CONFIG, "test-consumer-group");
		properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");


		try (KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<>(properties)) {
			TopicPartition topicPartition = new TopicPartition("test-message", userId % 10);
			kafkaConsumer.assign(Collections.singletonList(topicPartition));

			ConsumerRecords<String, String> consumerRecord = kafkaConsumer.poll(Duration.ofMillis(1000));

			for (ConsumerRecord<String, String> record : consumerRecord) {
				msg = "User: " + record.partition() + " has sent a msg : " + record.value();
			}
		}
		return msg;

	}
}
