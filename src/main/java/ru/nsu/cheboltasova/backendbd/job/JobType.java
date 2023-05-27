package ru.nsu.cheboltasova.backendbd.job;

public class JobType {
    private Integer jobID;
    private String jobPosition;

    public int getJobID() {return jobID;}

    public void setJobID(int jobID) {
        this.jobID = jobID;
    }

    public String getJobPosition() {
        return jobPosition;
    }

    public void setJobPosition(String jobPosition) {
        this.jobPosition = jobPosition;
    }
}
