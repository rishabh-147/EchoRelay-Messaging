package net.echorelay.messaging.config;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;

import java.util.Map;

public class CustomUserPartitioner implements Partitioner {

    @Override
    public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
        // Ensure key is not null before processing
        if (key == null) {
            return 0; // Default partition if no key is provided
        }

        // Convert key (userId) to a string
        String userId = key.toString();

        // Get number of partitions for the given topic
        int numPartitions = cluster.partitionCountForTopic(topic);

        // Hash userId to determine its partition, ensuring a non-negative result
        return Math.abs(userId.hashCode() % numPartitions);
    }

    @Override
    public void close() {}

    @Override
    public void configure(Map<String, ?> configs) {}
}

