package ru.nsu.cheboltasova.backendbd.employees;

public class PilotMedCheckUpType {
    private int employeeID;
    private String employeeFullName;
    private String skillval;

    public int getEmployeeID() { return employeeID; }
    public String getEmployeeFullName() { return employeeFullName; }
    public String getSkillval() { return skillval; }

    public void setEmployeeID(int employeeID) { this.employeeID = employeeID; }
    public void setEmployeeFullName(String employeeFullName) { this.employeeFullName = employeeFullName; }
    public void setSkillval(String skillval) { this.skillval = skillval; }
}
