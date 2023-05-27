package ru.nsu.cheboltasova.backendbd.passenger;

public class PassengerType {
    private Integer passengerID;
    private String passengerFullName;
    private int age;
    private String sex;
    private String passport;
    private String foreignPassport;

    public int getPassengerID() {
        return passengerID;
    }

    public void setPassengerID(int passengerID) {
        this.passengerID = passengerID;
    }

    public String getPassengerFullName() {
        return passengerFullName;
    }

    public void setPassengerFullName(String passengerFullName) {
        this.passengerFullName = passengerFullName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public String getForeignPassport() {
        return foreignPassport;
    }

    public void setForeignPassport(String foreignPassport) {
        this.foreignPassport = foreignPassport;
    }
}

