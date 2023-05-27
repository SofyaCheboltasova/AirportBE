package ru.nsu.cheboltasova.backendbd.aircrafts;

public class AircraftsType {
    private int aircraftID;
    private int brigadePilotsID;
    private int brigadeTechID;
    private int brigadeServiceID;
    private String aircraftType;
    private int age;
    private int numberOfSeats;
    private String airport;
    private int tankCapacity;

    public int getAircraftID() {
        return aircraftID;
    }

    public int getBrigadePilotsID() {
        return brigadePilotsID;
    }

    public int getBrigadeTechID() {
        return brigadeTechID;
    }

    public int getBrigadeServiceID() {
        return brigadeServiceID;
    }

    public String getAircraftType() {
        return aircraftType;
    }

    public int getAge() {
        return age;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public String getAirport() {
        return airport;
    }

    public int getTankCapacity() {
        return tankCapacity;
    }


    public void setAircraftID(int aircraftID) {
        this.aircraftID = aircraftID;
    }

    public void setBrigadePilotsID(int brigadePilotsID) {
        this.brigadePilotsID = brigadePilotsID;
    }

    public void setBrigadeTechID(int brigadeTechID) {
        this.brigadeTechID = brigadeTechID;
    }

    public void setBrigadeServiceID(int brigadeServiceID) {
        this.brigadeServiceID = brigadeServiceID;
    }

    public void setAircraftType(String aircraftType) {
        this.aircraftType = aircraftType;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public void setAirport(String airport) {
        this.airport = airport;
    }

    public void setTankCapacity(int tankCapacity) {
        this.tankCapacity = tankCapacity;
    }
}

