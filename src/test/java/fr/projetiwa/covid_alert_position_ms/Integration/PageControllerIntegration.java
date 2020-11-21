package fr.projetiwa.covid_alert_position_ms.Integration;

import com.github.database.rider.core.api.connection.ConnectionHolder;
import fr.projetiwa.covid_alert_position_ms.models.PositionService;
import fr.projetiwa.covid_alert_position_ms.util.Function;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import javax.sql.DataSource;

import static org.hamcrest.core.Is.is;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
public class PageControllerIntegration {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private Function service;

    @Autowired
    private DataSource dataSource;

    public ConnectionHolder getConnectionHolder() {
        // Return a function that retrieves a connection
        // from our data source
        return () -> dataSource.getConnection();
    }

    @Autowired
    private PositionService positionService;

    @Test
    public void testDonePosition() throws Exception {
        this.mockMvc.perform(get("/positions/donePosition")).andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testSetPosition() throws Exception {
        this.mockMvc.perform(get("/positions/setPosition")).andDo(print())
                .andExpect(status().isOk());
    }
}
