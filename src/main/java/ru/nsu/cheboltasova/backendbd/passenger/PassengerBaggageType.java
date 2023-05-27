package ru.nsu.cheboltasova.backendbd.passenger;

public class PassengerBaggageType extends PassengerType {
    private int flightID;
    private int baggageID;

    public int getBaggageID() {
        return baggageID;
    }

    public void setBaggageID(int id) {
        this.baggageID = id;
    }

    public int getFlightID() {
        return flightID;
    }

    public void setFlightID(int id) {
        this.flightID = id;
    }
}
