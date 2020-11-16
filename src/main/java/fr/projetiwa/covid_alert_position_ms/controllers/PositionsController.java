package fr.projetiwa.covid_alert_position_ms.controllers;

import fr.projetiwa.covid_alert_position_ms.models.*;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.sql.Timestamp;
import java.util.*;

@RestController
@RequestMapping("/positions")
public class PositionsController {

    @Autowired
    KafkaTemplate<String, Position> kafkaTemplate;

    @Autowired
    KafkaConsumer<String,Position> consumer;

    @Autowired
    private HttpServletRequest request;


/*
method for test
* */
    @PostMapping("/setPositionKafka")
    public String setPositionKafka (
            @RequestBody Coordinate location
    ){
        Principal user = request.getUserPrincipal();
        Position pos = new Position();
        pos.setLongitude(location.getLongitude());
        pos.setLatitude(location.getLatitude());
        pos.setPositionDate(new Timestamp((new Date()).getTime()));
        pos.setUserId(user.getName());
        kafkaTemplate.send("addPosition",pos);
        return "{\"success\":1}";
    }

    @Autowired
    private PositionService positionService;

    @GetMapping
    public List<Position> list () {
        return positionService.getPositionList();
    }


    @PostMapping("add/backup")
    public void addPositionToBackUp(@RequestBody Position position){
        //send a kafka to pipe
        kafkaTemplate.send("addSusPosition",position);
    }
}
