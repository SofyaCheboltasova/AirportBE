package ru.nsu.cheboltasova.backendbd.passenger;

public class BaggageType {
    private Integer baggageID;
    private int passengerID;
    private int flightID;
    private int weightKg;
    private String passengerFullName;

    public Integer getBaggageID() {
        return baggageID;
    }

    public void setBaggageID(Integer baggageID) {
        this.baggageID = baggageID;
    }

    public int getPassengerID() {
        return passengerID;
    }

    public void setPassengerID(int passengerID) {
        this.passengerID = passengerID;
    }

    public int getFlightID() {
        return flightID;
    }

    public void setFlightID(int flightID) {
        this.flightID = flightID;
    }

    public int getWeightKg() {
        return weightKg;
    }

    public void setWeightKg(int weightKg) {
        this.weightKg = weightKg;
    }

    public String getPassengerFullName() {
        return passengerFullName;
    }

    public void setPassengerFullName(String passengerFullName) {
        this.passengerFullName = passengerFullName;
    }
}

