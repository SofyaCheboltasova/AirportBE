package ru.nsu.cheboltasova.backendbd.passenger;

public class PassengerDateType extends PassengerType {
    private int ticketID;
    private int flightID;
    private String scheduleddeparture;

    public int getTicketID() {
        return ticketID;
    }

    public void setTicketID(int id) {
        this.ticketID = id;
    }

    public int getFlightID() {
        return flightID;
    }

    public void setFlightID(int id) {
        this.flightID = id;
    }

    public String getScheduleddeparture() {
        return scheduleddeparture;
    }
    public void setScheduleddeparture(String scheduleddeparture) {
        this.scheduleddeparture = scheduleddeparture;
    }
}
