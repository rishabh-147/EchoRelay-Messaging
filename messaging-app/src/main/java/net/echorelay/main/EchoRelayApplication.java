package net.echorelay.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.core.KafkaTemplate;

@SpringBootApplication(
	    scanBasePackages = "net.echorelay",
	    exclude = {org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration.class}
	)

public class EchoRelayApplication implements CommandLineRunner {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public static void main(String[] args) {
        SpringApplication.run(EchoRelayApplication.class, args);
    }

    @Override
    public void run(String... args) {
        kafkaTemplate.send("message-topic", "user123", "Hello, Kafka!");
        System.out.println("Message Sent via CommandLineRunner!");
    }
}
