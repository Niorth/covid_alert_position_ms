package fr.projetiwa.covid_alert_position_ms.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/positions")
public class PageController {

    @GetMapping({"/setPosition"})
    public String setPosition() {
        return "setPosition";
    }

    @GetMapping("/donePosition")
    public String donePosition() {
        return "donePosition";
    }
}
