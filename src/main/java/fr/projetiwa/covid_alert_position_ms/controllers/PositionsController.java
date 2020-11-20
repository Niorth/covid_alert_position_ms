package fr.projetiwa.covid_alert_position_ms.controllers;

import fr.projetiwa.covid_alert_position_ms.models.*;
import org.apache.commons.codec.binary.Base64;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
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
        pos.setAccuracy(location.getAccuracy());
        kafkaTemplate.send("addPosition",user.getName(), pos);
        return "{\"success\":1}";
    }

    @Autowired
    private PositionService positionService;

    /*
    Retourne une liste de positions appartenant au user du token
     */
    @GetMapping
    public List<Position> list (@RequestHeader (name="Authorization") String token) {
        String payload = token.split("\\.")[1];
        String personId = "";
        try {
            String str = new String(Base64.decodeBase64(payload), "UTF-8");
            JSONObject jsonObject = new JSONObject(str);
            personId = jsonObject.getString("sub");

        }catch(UnsupportedEncodingException e){
            e.printStackTrace();
        }
        return positionService.getPositionListByPersonId(personId);
    }


    @PostMapping("add/backup")
    public boolean addPositionToBackUp(@RequestBody Position position){
        //send a kafka to pipe
        try {
            kafkaTemplate.send("addSusPosition", position);
        }catch(Exception e){
            return false;
        }
        return true;
    }

    /*
    Ajoute au topic addSusPositions les positions des 7 derniers jours du user possedant le token
     */
    @PostMapping("/setSuspicious")
    public String setPostionToSuspicous(@RequestHeader (name="Authorization") String token){
        String payload = token.split("\\.")[1];
        try{
            String str = new String(Base64.decodeBase64(payload),"UTF-8");
            JSONObject jsonObject = new JSONObject(str);
            String personId = jsonObject.getString("sub");
            List<Position> positions = positionService.getPositionListByPersonId(personId);

            Calendar cal = Calendar.getInstance();
            cal.setTime(new Date());
            cal.add(Calendar.DATE, -7);
            Date dateBefore7Days = cal.getTime();
            for(Position p : positions){
                if(p.getPositionDate().after(dateBefore7Days)){
                    kafkaTemplate.send("addSusPosition", p);
                }
            }

        }catch(UnsupportedEncodingException e){
            e.printStackTrace();
        }

        return "{\"success\":1}";
    }

}
