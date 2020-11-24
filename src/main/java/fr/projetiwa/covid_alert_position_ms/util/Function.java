package fr.projetiwa.covid_alert_position_ms.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.projetiwa.covid_alert_position_ms.models.Position;
import fr.projetiwa.covid_alert_position_ms.services.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Configuration
public class Function {
    private static final ThreadLocal<DateFormat> dateFormatThreadLocal = new ThreadLocal<DateFormat>() {
        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat("yyyyMMdd");
        }
    };

    /**
     * Method to compare 2 dates
     * @param date1 a date
     * @param date2 a date
     * @return 0 if same 1 ou -1 if not the same
     */
    public static int compareDates(Date date1, Date date2) {
        DateFormat dateFormat = dateFormatThreadLocal.get();
        return dateFormat.format(date1).compareTo(dateFormat.format(date2));
    }

    /**
     * Transform an objet to a string
     * @param obj an object
     * @return a json string
     */
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Autowired
    private PositionService positionService;
    @Bean
    public void verifyPositionList(){
        if(positionService.getLastVerify() ==null){
            positionService.setLastVerify(new Date());
        }
        if(compareDates(positionService.getLastVerify(),new Date()) != 0) {
            positionService.setLastVerify(new Date());
            Calendar cal = Calendar.getInstance();
            cal.setTime(new Date());
            cal.add(Calendar.DATE, -7);
            Date dateBefore7Days = cal.getTime();
            List<Position> toDelete = new ArrayList<>();
            for (Position p : positionService.getPositionList()) {
                if (!(p.getPositionDate().after(new Timestamp(dateBefore7Days.getTime())))) {
                    toDelete.add(p);
                }
            }
            for (Position p : toDelete) {
                positionService.removePosition(p);
            }
        }


    }
}
