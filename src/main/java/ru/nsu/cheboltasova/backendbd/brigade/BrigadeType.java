package ru.nsu.cheboltasova.backendbd.brigade;

public class BrigadeType {
    private Integer brigadeID;
    private Integer departmentID;
    private int brigadeType;

    public int getDepartmentID() { return departmentID; }
    public int getBrigadeID() { return brigadeID; }
    public int getBrigadeType() { return brigadeType; }

    public void setDepartmentID(int departmentID) { this.departmentID = departmentID; }
    public void setBrigadeID(int brigadeID) { this.brigadeID = brigadeID; }
    public void setBrigadeType(int brigadeType) { this.brigadeType = brigadeType; }
}

