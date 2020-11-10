package fr.projetiwa.covid_alert_position_ms.models;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;



public class Position extends SuperClassPosition {

    private String userId;

    public Position(Float longitude, Timestamp positionDate, Float latitude, String userId) {
        super(longitude, positionDate, latitude);
        this.userId = userId;
    }

    public Position() { }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}