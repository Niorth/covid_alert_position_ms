package fr.projetiwa.covid_alert_position_ms.controllers;



import fr.projetiwa.covid_alert_position_ms.models.Position;
import fr.projetiwa.covid_alert_position_ms.repositories.PositionsRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.Console;
import java.sql.Timestamp;
import java.util.*;

@RestController
@RequestMapping("/positions")
public class SuspiciousPositionController {

    @Autowired
    private PositionsRepository positionsRepository ;



    @GetMapping
    public List<Position> list () {

        return positionsRepository.findAll(); }
    @GetMapping
    @RequestMapping("{id}")
    public Position get ( @PathVariable Long id ) {
        return positionsRepository.getOne(id); }
    @PostMapping
    @ResponseStatus ( HttpStatus.CREATED )
    public Position create(@RequestBody final Position sus) {
        return positionsRepository.saveAndFlush((Position)sus); }


}
