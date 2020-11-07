package fr.projetiwa.covid_alert_position_ms.models;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@MappedSuperclass
public class SuperClassPosition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long position_id;
    protected Float longitude;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    protected Timestamp positionDate;
    protected Float latitude;

    public long getPosition_id() {
        return position_id;
    }

    public void setPosition_id(long position_id) {
        this.position_id = position_id;
    }

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

    public SuperClassPosition(long position_id, Float longitude, Timestamp positionDate, Float latitude) {
        this.position_id = position_id;
        this.longitude = longitude;
        this.positionDate = positionDate;
        this.latitude = latitude;
    }
    public SuperClassPosition(){}
}