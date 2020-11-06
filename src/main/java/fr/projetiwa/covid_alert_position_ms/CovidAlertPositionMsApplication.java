package fr.projetiwa.covid_alert_position_ms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CovidAlertPositionMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CovidAlertPositionMsApplication.class, args);
	}

}
