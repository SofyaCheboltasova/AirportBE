package ru.nsu.cheboltasova.backendbd.aircrafts;

public class AircraftAndArrivalTimeType extends AircraftsType {
    private String airname;
    private String scheduledarrival;

    public String getAirname() {
        return airname;
    }

    public void setAirname(String airname) {
        this.airname = airname;
    }

    public String getScheduledarrival() {
        return scheduledarrival;
    }

    public void setScheduledarrival(String scheduledarrival) {
        this.scheduledarrival = scheduledarrival;
    }
}
