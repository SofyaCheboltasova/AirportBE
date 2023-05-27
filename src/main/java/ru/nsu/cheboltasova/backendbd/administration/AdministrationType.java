package ru.nsu.cheboltasova.backendbd.administration;

import java.sql.Timestamp;

public class AdministrationType {
    private Integer chiefID;
    private String chiefFullName;
    private Timestamp age;
    private String sex;
    private int salary;
    private int lengthOfService;
    private boolean hasChildren;
    private Integer quantityOfChildren;

    public int getChiefID() {
        return chiefID;
    }

    public void setChiefID(int chiefID) {
        this.chiefID = chiefID;
    }

    public String getChiefFullName() {
        return chiefFullName;
    }

    public void setChiefFullName(String chiefFullName) {
        this.chiefFullName = chiefFullName;
    }

    public Timestamp getAge() {
        return age;
    }

    public void setAge(Timestamp age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public int getLengthOfService() {
        return lengthOfService;
    }

    public void setLengthOfService(int lengthOfService) {
        this.lengthOfService = lengthOfService;
    }

    public boolean isHasChildren() {
        return hasChildren;
    }

    public void setHasChildren(boolean hasChildren) {
        this.hasChildren = hasChildren;
    }

    public Integer getQuantityOfChildren() {
        return quantityOfChildren;
    }

    public void setQuantityOfChildren(Integer quantityOfChildren) {
        this.quantityOfChildren = quantityOfChildren;
    }
}