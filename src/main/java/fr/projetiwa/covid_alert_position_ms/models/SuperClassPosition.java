package fr.projetiwa.covid_alert_position_ms.models;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@MappedSuperclass
public class SuperClassPosition {

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    protected Timestamp positionDate;
    protected Float longitude;
    protected Float latitude;

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    public Timestamp getPositionDate() {
        return positionDate;
    }

    public void setPositionDate(Timestamp positionDate) {
        this.positionDate = positionDate;
    }

    public SuperClassPosition(Float longitude, Timestamp positionDate, Float latitude) {
        this.longitude = longitude;
        this.positionDate = positionDate;
        this.latitude = latitude;
    }
    public SuperClassPosition(){}
}