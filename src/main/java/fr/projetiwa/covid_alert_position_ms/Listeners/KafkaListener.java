package fr.projetiwa.covid_alert_position_ms.Listeners;



import fr.projetiwa.covid_alert_position_ms.models.Position;
import fr.projetiwa.covid_alert_position_ms.models.PositionService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import org.springframework.kafka.annotation.PartitionOffset;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

import org.springframework.web.client.RestTemplate;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;


@Service
public class KafkaListener {


    @Autowired
    private PositionService positionService;

    @org.springframework.kafka.annotation.KafkaListener(id = "thing4", topicPartitions =
            { @TopicPartition(topic = "addPosition", partitions = { "0" },
                    partitionOffsets = @PartitionOffset(partition = "*", initialOffset = "0"))
            })
    public void addNewPosition(@Header(KafkaHeaders.RECEIVED_TIMESTAMP)long time,@Header(name= KafkaHeaders.RECEIVED_MESSAGE_KEY, required = false) String key, Position position) {
        //j'ajoute une position à mon singleton
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE, -7);
        Date dateBefore7Days = cal.getTime();
        System.out.println(key);
        if(new Timestamp(time).after(new Timestamp(dateBefore7Days.getTime()))){

            //on passe le temps du kafka dans la position
            position.setPositionDate(new Timestamp(time));
            positionService.addPosition(position);
        }



        
    }
}
