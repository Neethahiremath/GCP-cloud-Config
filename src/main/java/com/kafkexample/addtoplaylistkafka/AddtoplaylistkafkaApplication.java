package com.kafkexample.addtoplaylistkafka;

import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.newsworth.deserializer.NWAddtoplaylistDeserializer;
import com.nw.minibean.NWMinAddToPlayList;

//@SpringBootApplication
public class AddtoplaylistkafkaApplication {

	public static void main(String[] args) {
		SpringApplication.run(AddtoplaylistkafkaApplication.class, args);
		getProperty();

	}

	public static void getProperty() {
		Properties configs = new Properties();
		configs.put("bootstrap.servers", "192.168.90.203:9092");
		configs.put("group.id", "foo");
		configs.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		configs.put("value.deserializer", NWAddtoplaylistDeserializer.class);

		KafkaConsumer<String, NWMinAddToPlayList> consumer = new KafkaConsumer<String, NWMinAddToPlayList>(configs);
		consumer.subscribe(Arrays.asList("inAddToPlayList"));
		while (true) {
			ConsumerRecords<String, NWMinAddToPlayList> record = consumer.poll(100);
			for (ConsumerRecord<String, NWMinAddToPlayList> records : record) {
				System.out.println("consumed value is :::" + records.value());
			}
		}

	}

}
