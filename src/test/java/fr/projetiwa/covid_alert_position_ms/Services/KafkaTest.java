package fr.projetiwa.covid_alert_position_ms.Services;


import fr.projetiwa.covid_alert_position_ms.models.Position;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.poi.ss.formula.functions.T;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class KafkaTest {

    @Autowired
    KafkaConsumer<String, Position> consumer;
    @Autowired
    KafkaTemplate<String,Position> kafkaTemplate;

    @Test
    public void test() throws Exception{
        Position position = new Position(1f,new Timestamp(new Date().getTime()),1f,"test");
        kafkaTemplate.send("testKafkaP",position);
        consumer.subscribe(Arrays.asList("testKafkaP"));
        List<Position> list = new ArrayList<>();
        ConsumerRecords<String, Position> records = consumer.poll(5000);
        for (ConsumerRecord<String, Position> record : records)
            list.add(record.value());

        assertThat(list.get(list.size()-1) == position).isTrue();








    }

}
