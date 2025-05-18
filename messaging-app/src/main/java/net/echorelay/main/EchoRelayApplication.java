package net.echorelay.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import net.echorelay.kafkaConsumer.ConsumerService;
import net.echorelay.kafkaProducer.ProducerService;

@SpringBootApplication(scanBasePackages = "net.echorelay")
public class EchoRelayApplication {

    public static void main(String[] args) {
        // Start Spring context and get the beans manually
        ConfigurableApplicationContext context = SpringApplication.run(EchoRelayApplication.class, args);

        // Retrieve Spring-managed beans
        ProducerService producer = context.getBean(ProducerService.class);
        ConsumerService consumerService = context.getBean(ConsumerService.class);

        System.out.println("Hello World");

        try {
            producer.sendToUser(0, "Hiiii !");
            System.out.println(consumerService.recieveMsg(0));	
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
