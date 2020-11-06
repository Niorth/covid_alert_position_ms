package fr.projetiwa.covid_alert_position_ms.repositories;

import fr.projetiwa.covid_alert_position_ms.models.Position;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PositionsRepository extends JpaRepository<Position,Long> { }