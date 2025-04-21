package com.example.alif.metlife.inzamamulhoque_1910014;

public class EmployeeModel {
    private String employeeName;
    private String employeeID;
    private String employeeAddress;
    private String employeeContactNo;
    private String employeeContract;


    public EmployeeModel(String employeeName, String employeeID, String employeeAddress,String employeeContactNo, String employeeContract) {
        this.employeeName = employeeName;
        this.employeeID = employeeID;
        this.employeeAddress=employeeAddress;
        this.employeeContactNo = employeeContactNo;
        this.employeeContract = employeeContract;
    }

    public String getEmployeeAddress() {
        return employeeAddress;
    }

    public void setEmployeeAddress(String employeeAddress) {
        this.employeeAddress = employeeAddress;
    }

    // Getters and Setters
    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    public String getEmployeeContactNo() {
        return employeeContactNo;
    }

    public void setEmployeeContactNo(String employeeContactNo) {
        this.employeeContactNo = employeeContactNo;
    }

    public String getEmployeeContract() {
        return employeeContract;
    }

    public void setEmployeeContract(String employeeContract) {
        this.employeeContract = employeeContract;
    }
}
