package net.echorelay.kafkaProducer;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.stereotype.Component;

@Component
public class ProducerService {

	public void sendToUser(int userId, String msg) {

		Properties properties = new Properties();

		properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

		KafkaProducer<String, String> kafkaProducer = new KafkaProducer<String, String>(properties);

		ProducerRecord<String, String> producerRecord = new ProducerRecord<String, String>("test-message", userId % 10,
				"KEY" + (userId % 10), msg);

		kafkaProducer.send(producerRecord, (metadata, exception) -> {
			if (exception == null) {
				System.out.print("message-sent-to-topic-----------" + metadata.topic());
				System.out.println("---For User: " + metadata.partition());
			} else {
				exception.printStackTrace();
			}
		});
		
		  kafkaProducer.flush();  // Ensure message is sent before closing
		  kafkaProducer.close();  // Properly close the producer
	}

}
