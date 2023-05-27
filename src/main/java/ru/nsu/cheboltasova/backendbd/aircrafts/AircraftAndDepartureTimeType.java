package ru.nsu.cheboltasova.backendbd.aircrafts;

public class AircraftAndDepartureTimeType extends AircraftsType {
    private String airname;
    private String scheduleddeparture;

    public String getAirname() {
        return airname;
    }

    public void setAirname(String airname) {
        this.airname = airname;
    }

    public String getScheduleddeparture() {
        return scheduleddeparture;
    }

    public void setScheduleddeparture(String scheduleddeparture) {
        this.scheduleddeparture = scheduleddeparture;
    }
}
