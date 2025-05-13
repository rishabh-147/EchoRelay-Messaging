package net.echorelay.kafkaProducer;
	
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public KafkaProducerService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String topic, String userId, String message) {
        int partition = Math.abs(userId.hashCode() % 3); // Assuming 3 partitions
        kafkaTemplate.send(topic, partition, userId, message);
    }
}


