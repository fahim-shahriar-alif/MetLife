package com.example.alif.metlife.inzamamulhoque_1910014;

public class EmployeeJob {
    private String jobPosition;
    private String jobDescription;
    private String jobRequirement;
    private String salaryRange;

    public EmployeeJob(String jobPosition, String jobDescription, String jobRequirement, String salaryRange) {
        this.jobPosition = jobPosition;
        this.jobDescription = jobDescription;
        this.jobRequirement = jobRequirement;
        this.salaryRange = salaryRange;
    }

    public String getJobPosition() {
        return jobPosition;
    }

    public void setJobPosition(String jobPosition) {
        this.jobPosition = jobPosition;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public String getJobRequirement() {
        return jobRequirement;
    }

    public void setJobRequirement(String jobRequirement) {
        this.jobRequirement = jobRequirement;
    }

    public String getSalaryRange() {
        return salaryRange;
    }

    public void setSalaryRange(String salaryRange) {
        this.salaryRange = salaryRange;
    }
}
