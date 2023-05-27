package ru.nsu.cheboltasova.backendbd.flight;

public class FlightType {
    private int flightID;
    private int aircraftID;
    private String aircraftType;
    private int flightTypeID;
    private String flightType;
    private Integer defaultTicketCost;
    private Integer numberOfSeats;

    public int getFlightID() {
        return flightID;
    }

    public void setFlightID(int flightID) {
        this.flightID = flightID;
    }

    public int getAircraftID() {
        return aircraftID;
    }

    public void setAircraftID(int aircraftID) {
        this.aircraftID = aircraftID;
    }

    public String getAircraftType() {
        return aircraftType;
    }

    public void setAircraftType(String aircraftType) {
        this.aircraftType = aircraftType;
    }

    public int getFlightTypeID() {
        return flightTypeID;
    }

    public void setFlightTypeID(int flightTypeID) {
        this.flightTypeID = flightTypeID;
    }

    public String getFlightType() {
        return flightType;
    }

    public void setFlightType(String flightType) {
        this.flightType = flightType;
    }

    public Integer getDefaultTicketCost() {
        return defaultTicketCost;
    }

    public void setDefaultTicketCost(Integer defaultTicketCost) {
        this.defaultTicketCost = defaultTicketCost;
    }

    public Integer getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(Integer numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }
}

