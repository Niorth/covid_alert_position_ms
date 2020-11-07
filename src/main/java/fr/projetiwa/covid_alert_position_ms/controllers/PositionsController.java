package fr.projetiwa.covid_alert_position_ms.controllers;



import fr.projetiwa.covid_alert_position_ms.models.Position;
import fr.projetiwa.covid_alert_position_ms.models.PositionService;
import fr.projetiwa.covid_alert_position_ms.models.SuperClassPosition;
import fr.projetiwa.covid_alert_position_ms.models.SuspiciousPosition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/positions")
public class PositionsController {

    @Autowired
    KafkaTemplate<String, Position> kafkaTemplate;


    @GetMapping("/kafka")
    public void kafka (){
        Position pos = new Position();
        pos.setLongitude(1f);
        pos.setLatitude(1f);
        pos.setPositionDate(new Timestamp((new Date()).getTime()));
        kafkaTemplate.send("addPosition",pos);
    }
    @Autowired
    private PositionService positionService;

    @GetMapping
    public List<Position> list () {
        return positionService.getPositionList(); }

    @PostMapping("add/backup")
    public void addPositionToBackUp(@RequestBody Position position){

        //send a kafka to pipe
        kafkaTemplate.send("addSusPosition",position);
    }
    @GetMapping
    @RequestMapping("{id}")
    public Position get ( @PathVariable Long id ) {
        List<Position> list = positionService.getPositionList().stream().filter(n -> n.getPosition_id() == id).collect(Collectors.toList());
        return list.get(0);
    }



}
