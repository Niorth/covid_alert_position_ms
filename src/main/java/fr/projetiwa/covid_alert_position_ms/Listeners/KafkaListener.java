package fr.projetiwa.covid_alert_position_ms.Listeners;



import fr.projetiwa.covid_alert_position_ms.models.Position;
import fr.projetiwa.covid_alert_position_ms.models.PositionService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import org.springframework.kafka.annotation.PartitionOffset;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.stereotype.Service;

import org.springframework.web.client.RestTemplate;




@Service
public class KafkaListener {


    @Autowired
    private PositionService positionService;

    @org.springframework.kafka.annotation.KafkaListener(id = "thing3", topicPartitions =
            { @TopicPartition(topic = "addPosition", partitions = { "0" },
                    partitionOffsets = @PartitionOffset(partition = "*", initialOffset = "0"))
            })
    public void addNewPosition(Position position) {
        positionService.addPosition(position);


    }
}
