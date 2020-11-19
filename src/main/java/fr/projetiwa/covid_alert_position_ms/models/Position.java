package fr.projetiwa.covid_alert_position_ms.models;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;



public class Position extends SuperClassPosition {

    private String userId;
    private float accuracy;

    public Position(Float longitude, Timestamp positionDate, Float latitude, String userId, float accuracy) {
        super(longitude, positionDate, latitude);
        this.userId = userId;
        this.accuracy = accuracy;
    }

    public Position() { }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public float getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(float accuracy) {
        this.accuracy = accuracy;
    }
}