package com.github.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class Producer {

    public static void main(String[] args) throws Exception {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "192.168.2.14:9092");
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        KafkaProducer kafkaProducer = new KafkaProducer(properties);
        for (int i = 0; i < 100; i++) {
            System.out.println(i);
            Thread.sleep(10);
            kafkaProducer.send(new ProducerRecord("t1", Integer.toString(i), "test message - " + i));
        }
    }
}