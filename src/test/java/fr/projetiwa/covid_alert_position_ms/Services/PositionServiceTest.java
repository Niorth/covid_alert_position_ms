package fr.projetiwa.covid_alert_position_ms.Services;

import fr.projetiwa.covid_alert_position_ms.models.Position;
import fr.projetiwa.covid_alert_position_ms.models.PositionService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.util.Date;

@SpringBootTest
public class PositionServiceTest {
    @Autowired
    private PositionService positionService;

    @Test
    @DisplayName("Test clear positions")
    public void clearPositionsTest(){
        positionService.clearPositions();
        Position pos = new Position(1f,new Timestamp(new Date().getTime()),1f,"test");
        positionService.addPosition(pos);
        positionService.clearPositions();
        Assertions.assertEquals(0,positionService.getPositionList().size(),"should be the same");
    }

    @Test
    @DisplayName("Test add position")
    public void addPositionTest(){
        positionService.clearPositions();
        Position pos = new Position(1f,new Timestamp(new Date().getTime()),1f,"test");
        positionService.addPosition(pos);
        Assertions.assertEquals(pos,positionService.getPositionList().get(positionService.getPositionList().size()-1),"should be the same");


    }
    @Test
    @DisplayName("Test remove position")
    public void removePositionTest(){
        positionService.clearPositions();
        Position pos = new Position(1f,new Timestamp(new Date().getTime()),1f,"test");
        Position pos2 = new Position(2f,new Timestamp(new Date().getTime()),2f,"test");
        positionService.addPosition(pos);
        positionService.addPosition(pos2);
        positionService.removePosition(pos2);
        Assertions.assertEquals(pos,positionService.getPositionList().get(positionService.getPositionList().size()-1),"should be the same");



    }

    @Test
    @DisplayName("Test consult positions")
    public void consultPositionTest(){
        positionService.clearPositions();
        Position pos = new Position(1f,new Timestamp(new Date().getTime()),1f,"test");
        Position pos2 = new Position(2f,new Timestamp(new Date().getTime()),2f,"test");
        positionService.addPosition(pos);
        positionService.addPosition(pos2);
        Assertions.assertEquals(2,positionService.getPositionList().size(),"should be the same");
    }


    @Test
    @DisplayName("Test last verify day")
    public void lastVerifyTest(){
        positionService.clearPositions();
        Date now = new Date();
        positionService.setLastVerify(now);
        Assertions.assertEquals(now,positionService.getLastVerify(),"should be the same");

    }



}
