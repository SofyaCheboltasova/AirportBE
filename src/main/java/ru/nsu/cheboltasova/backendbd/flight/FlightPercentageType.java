package ru.nsu.cheboltasova.backendbd.flight;

public class FlightPercentageType extends FlightFreeSeatsType {
    private int percentageCount;

    public int getPercentageCount() {
        return percentageCount;
    }

    public void setPercentageCount(int percentageCount) {
        this.percentageCount = percentageCount;
    }
}

