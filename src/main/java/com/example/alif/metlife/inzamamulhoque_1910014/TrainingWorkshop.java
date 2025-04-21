package com.example.alif.metlife.inzamamulhoque_1910014;

import java.time.LocalDate;

public class TrainingWorkshop {
    private String employeeName;
    private String employeeID;
    private LocalDate startTime;
    private LocalDate endTime;
    private String trainingOrWorkshopContent;

    public TrainingWorkshop(String employeeName, String employeeID, LocalDate startTime, LocalDate endTime, String trainingOrWorkshopContent) {
        this.employeeName = employeeName;
        this.employeeID = employeeID;
        this.startTime = startTime;
        this.endTime = endTime;
        this.trainingOrWorkshopContent = trainingOrWorkshopContent;
    }

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

    public LocalDate getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDate startTime) {
        this.startTime = startTime;
    }

    public LocalDate getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDate endTime) {
        this.endTime = endTime;
    }

    public String getTrainingOrWorkshopContent() {
        return trainingOrWorkshopContent;
    }

    public void setTrainingOrWorkshopContent(String trainingOrWorkshopContent) {
        this.trainingOrWorkshopContent = trainingOrWorkshopContent;
    }
}
