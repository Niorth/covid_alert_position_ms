package fr.projetiwa.covid_alert_position_ms.services;

import fr.projetiwa.covid_alert_position_ms.models.Position;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PositionService {
    private List<Position> positionList;
    private Date lastVerify;

    /**
     * to get the list of positions
     * @return
     */
    public List<Position> getPositionList() {
        return positionList;
    }

    /**
     * to set the latest date of the verification
     * @param date
     */
    public void setLastVerify(Date date){
        this.lastVerify=date;
    }
    /**
     * to get the latest date of the verification
     */
    public Date getLastVerify(){
        return this.lastVerify;
    }

    /**
     * to set the list of positions
     * @param positionList une liste de position
     */
    public void setPositionList(List<Position> positionList) {
        this.positionList = positionList;
    }
    /**
     * to remove a position in the list
     * @param position the position to remove
     */
    public void removePosition(Position position){
        this.positionList.remove(position);
    }
    /**
     * to add a position in the list
     * @param position the position to remove
     */
    public void addPosition(Position position){
        this.positionList.add(position);
    }

    /**
     * to clear the list of the positions list
     */
    public void clearPositions(){
        this.positionList.clear();
    }

    public PositionService(List<Position> positionList) {
        this.positionList = positionList;
        this.lastVerify = null;
    }

    public List<Position> getPositionListByPersonId(String personId){
        List<Position> sortedPosition = new ArrayList<>();
        for(Position p: positionList){
            if(personId != null && p.getUserId() != null){
                if(p.getUserId().equals(personId)){
                    sortedPosition.add(p);
                }
            }
        }
        return sortedPosition;
    }
}
