package fr.projetiwa.covid_alert_position_ms.models;

import java.util.List;

public class PositionService {
    private List<Position> positionList;

    public List<Position> getPositionList() {
        return positionList;
    }

    public void setPositionList(List<Position> positionList) {
        this.positionList = positionList;
    }
    public void addPosition(Position position){
        this.positionList.add(position);
    }

    public PositionService(List<Position> positionList) {
        this.positionList = positionList;
    }
}
