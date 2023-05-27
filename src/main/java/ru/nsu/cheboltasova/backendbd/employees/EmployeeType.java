package ru.nsu.cheboltasova.backendbd.employees;

public class EmployeeType {
    private int employeeID;
    private int brigadeID;
    private int departmentID;
    private int jobID;
    private String employeeFullName;
    private int age;
    private String sex;
    private int salary;
    private int lengthOfService;
    private boolean hasChildren;
    private Integer quantityOfChildren;

    public int getEmployeeID() { return employeeID; }
    public int getBrigadeID() { return brigadeID; }
    public int getDepartmentID() { return departmentID; }
    public int getJobID() { return jobID; }
    public String getEmployeeFullName() { return employeeFullName; }
    public int getAge() { return age; }
    public String getSex() { return sex; }
    public int getSalary() { return salary; }
    public int getLengthOfService() { return lengthOfService; }
    public boolean isHasChildren() { return hasChildren; }
    public Integer getQuantityOfChildren() { return quantityOfChildren; }


    public void setEmployeeID(int employeeID) { this.employeeID = employeeID; }
    public void setBrigadeID(int brigadeID) { this.brigadeID = brigadeID; }
    public void setDepartmentID(int departmentID) { this.departmentID = departmentID; }
    public void setJobID(int jobID) { this.jobID = jobID; }
    public void setEmployeeFullName(String employeeFullName) { this.employeeFullName = employeeFullName; }
    public void setAge(int age) { this.age = age; }
    public void setSex(String sex) { this.sex = sex; }
    public void setSalary(int salary) { this.salary = salary; }
    public void setLengthOfService(int lengthOfService) { this.lengthOfService = lengthOfService; }
    public void setHasChildren(boolean hasChildren) { this.hasChildren = hasChildren; }
    public void setQuantityOfChildren(Integer quantityOfChildren) { this.quantityOfChildren = quantityOfChildren; }
}

