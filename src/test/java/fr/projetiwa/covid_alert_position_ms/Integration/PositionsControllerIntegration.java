package fr.projetiwa.covid_alert_position_ms.Integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.database.rider.core.api.connection.ConnectionHolder;
import fr.projetiwa.covid_alert_position_ms.models.Coordinate;
import fr.projetiwa.covid_alert_position_ms.models.Position;
import fr.projetiwa.covid_alert_position_ms.models.PositionService;
import fr.projetiwa.covid_alert_position_ms.util.Function;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import javax.sql.DataSource;
import java.security.Principal;
import java.sql.Timestamp;
import java.util.Date;

import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
public class PositionsControllerIntegration {
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
    @DisplayName("GET /positions - Found")
    void testGetPositionsFound() throws Exception {
        positionService.clearPositions();
        positionService.addPosition(new Position(1f,new Timestamp(new Date().getTime()),1f,"test", 0));
        positionService.addPosition(new Position(1f,new Timestamp(new Date().getTime()),1f,"test", 0));
        mockMvc.perform(get("/positions",1))
                // Validate the status code and content type
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                // Validate the returned fields
                .andExpect(jsonPath("$.length()", is(2)));
    }

    @Test
    @DisplayName("POST /setPositionKafka - Found")
    void testSetPositionKafka() throws Exception {

        Principal mockPrincipal = Mockito.mock(Principal.class);
        Mockito.when(mockPrincipal.getName()).thenReturn("testId");
        ObjectMapper objectMapper = new ObjectMapper();
        Coordinate nullIsland = new Coordinate((float) 0, (float) 0, (float) 0);
        String nullIslandString = objectMapper.writeValueAsString(nullIsland);
        mockMvc.perform(post("/positions/setPositionKafka")
                .principal(mockPrincipal)
                .contentType("application/json")
                .content(nullIslandString))
                // Validate the returned fields
                .andExpect(jsonPath("$.success", is(1)));
    }
}

