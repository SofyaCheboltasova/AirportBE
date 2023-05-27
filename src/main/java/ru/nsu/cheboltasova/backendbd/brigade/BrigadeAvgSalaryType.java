package ru.nsu.cheboltasova.backendbd.brigade;

public class BrigadeAvgSalaryType {
    private int brigadeID;
    private String employeeFullName;
    private int avgSalary;

    public String getEmployeeFullName() { return employeeFullName; }
    public int getBrigadeID() { return brigadeID; }
    public int getSalary() { return avgSalary; }

    public void setEmployeeFullName(String employeeFullName) { this.employeeFullName = employeeFullName; }
    public void setBrigadeID(int brigadeID) { this.brigadeID = brigadeID; }
    public void setSalary(int salary) { this.avgSalary = salary; }
}
