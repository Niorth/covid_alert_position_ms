package fr.projetiwa.covid_alert_position_ms.models;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;
import java.util.Set;


@Entity(name="covid_state")
@Access(AccessType.FIELD)
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Covid_state {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long state_id;
    private String state_label;

    @OneToMany(mappedBy = "covid_state")

    List<Person_state> state_persons;


}