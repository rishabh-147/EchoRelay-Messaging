package net.echorelay.kafkaConsumer;
	
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;


@Service
public class KafkaConsumerService {

    @KafkaListener(topics = "message-topic", groupId = "user-group")
    public void consumeMessage(@Header(name = "kafka_receivedPartitionId") int partition,
                               @Header(name = "kafka_receivedMessageKey") String userId,
                               String message) {
        System.out.println("Received message for User " + userId + " in Partition " + partition + ": " + message);
    }

}
