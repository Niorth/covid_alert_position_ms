package fr.projetiwa.covid_alert_position_ms.util;

import fr.projetiwa.covid_alert_position_ms.models.Position;
import fr.projetiwa.covid_alert_position_ms.models.PositionService;
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
    public static int compareDates(Date date1, Date date2) {
        DateFormat dateFormat = dateFormatThreadLocal.get();
        return dateFormat.format(date1).compareTo(dateFormat.format(date2));
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
