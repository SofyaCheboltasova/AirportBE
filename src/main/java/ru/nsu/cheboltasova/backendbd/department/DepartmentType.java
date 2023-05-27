package ru.nsu.cheboltasova.backendbd.department;

public class DepartmentType {
    private Integer departmentID;
    private Integer chiefID;
    private Integer jobID;

    public int getDepartmentID() { return departmentID; }
    public int getChiefID() { return chiefID; }
    public int getJobID() { return jobID; }

    public void setDepartmentID(int departmentID) { this.departmentID = departmentID; }
    public void setChiefID(int chiefID) { this.chiefID = chiefID; }
    public void setJobID(int jobID) { this.jobID = jobID; }
}

