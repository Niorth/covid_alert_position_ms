package fr.projetiwa.covid_alert_position_ms.listeners;



import fr.projetiwa.covid_alert_position_ms.models.Position;
import fr.projetiwa.covid_alert_position_ms.models.PositionService;
import fr.projetiwa.covid_alert_position_ms.util.Function;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.kafka.annotation.PartitionOffset;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;


@Service
public class KafkaListener {


    @Autowired
    private PositionService positionService;

    @Autowired
    private Function service;

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
        //afin de vérifier si les localisations dans le Singleton sont bien inférieur à 7j
        service.verifyPositionList();
        if(new Timestamp(time).after(new Timestamp(dateBefore7Days.getTime()))){
            //on passe le temps du kafka dans la position
            position.setPositionDate(new Timestamp(time));
            //on passe la clé dans la position
            position.setUserId(key);
            positionService.addPosition(position);
        }



        
    }
}
