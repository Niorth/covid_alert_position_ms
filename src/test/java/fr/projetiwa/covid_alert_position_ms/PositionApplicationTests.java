package fr.projetiwa.covid_alert_position_ms;

import fr.projetiwa.covid_alert_position_ms.controllers.PositionsController;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.listener.MessageListener;

import java.util.concurrent.CountDownLatch;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class PositionApplicationTests {

	@Autowired
	private PositionsController positionsController;

	@Test
	void contextLoads()  throws Exception{
		assertThat(positionsController).isNotNull();

	}

}
