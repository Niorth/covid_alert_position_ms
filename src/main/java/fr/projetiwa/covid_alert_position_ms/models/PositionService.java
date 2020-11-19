package fr.projetiwa.covid_alert_position_ms.models;

import java.util.Date;
import java.util.List;

public class PositionService {
    private List<Position> positionList;
    private Date lastVerify;

    public List<Position> getPositionList() {
        return positionList;
    }

    public void setLastVerify(Date date){
        this.lastVerify=date;
    }
    public Date getLastVerify(){
        return this.lastVerify;
    }

    public void setPositionList(List<Position> positionList) {
        this.positionList = positionList;
    }

    public void removePosition(Position position){
        this.positionList.remove(position);
    }

    public void addPosition(Position position){
        this.positionList.add(position);
    }

    public void clearPositions(){
        this.positionList.clear();
    }

    public PositionService(List<Position> positionList) {
        this.positionList = positionList;
        this.lastVerify = null;
    }
}
