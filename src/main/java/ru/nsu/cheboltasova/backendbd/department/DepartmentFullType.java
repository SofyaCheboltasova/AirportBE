package ru.nsu.cheboltasova.backendbd.department;

public class DepartmentFullType extends DepartmentType {
    private String jobposition;
    private String chieffullname;

    public String getChieffullname() { return chieffullname; }
    public String getJobposition() { return jobposition; }

    public void setJobposition(String jobposition) { this.jobposition = jobposition; }
    public void setChieffullname(String chieffullname) { this.chieffullname = chieffullname; }
}
