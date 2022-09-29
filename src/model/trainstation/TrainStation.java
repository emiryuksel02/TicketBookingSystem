package model.trainstation;

/**
 * Represents a train station.
 * 
 * @author Emir Yuksel
 * @version 1.0
 */
public class TrainStation {

    private String id;

    public TrainStation(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
