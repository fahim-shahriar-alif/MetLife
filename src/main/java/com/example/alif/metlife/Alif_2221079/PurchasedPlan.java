package com.example.alif.metlife.Alif_2221079;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import java.util.ArrayList;
import java.util.List;

public class PurchasedPlan {
    private final StringProperty planID;
    private final StringProperty planName;
    private final StringProperty premium;
    private final StringProperty coverage;
    private final StringProperty term;
    private List<PaymentRecord> paymentHistory;

    public PurchasedPlan(String planID, String planName, String premium, String coverage, String term) {
        this.planID = new SimpleStringProperty(planID);
        this.planName = new SimpleStringProperty(planName);
        this.premium = new SimpleStringProperty(premium);
        this.coverage = new SimpleStringProperty(coverage);
        this.term = new SimpleStringProperty(term);
        this.paymentHistory = new ArrayList<>();
    }

    public String getPlanID() {
        return planID.get();
    }

    public void setPlanID(String planID) {
        this.planID.set(planID);
    }

    public StringProperty planIDProperty() {
        return planID;
    }

    public String getPlanName() {
        return planName.get();
    }

    public void setPlanName(String planName) {
        this.planName.set(planName);
    }

    public StringProperty planNameProperty() {
        return planName;
    }

    public String getPremium() {
        return premium.get();
    }

    public void setPremium(String premium) {
        this.premium.set(premium);
    }

    public StringProperty premiumProperty() {
        return premium;
    }

    public String getCoverage() {
        return coverage.get();
    }

    public void setCoverage(String coverage) {
        this.coverage.set(coverage);
    }

    public StringProperty coverageProperty() {
        return coverage;
    }

    public String getTerm() {
        return term.get();
    }

    public void setTerm(String term) {
        this.term.set(term);
    }

    public StringProperty termProperty() {
        return term;
    }

    public List<PaymentRecord> getPaymentHistory() {
        return paymentHistory;
    }

    public void addPayment(PaymentRecord payment) {
        paymentHistory.add(payment);
    }

    public boolean isMonthPaid(int year, int month) {
        return paymentHistory.stream()
                .anyMatch(payment -> payment.getYear() == year && payment.getMonth() == month);
    }

    public static class PaymentRecord {
        private int year;
        private int month;
        private String amount;
        private String status;

        public PaymentRecord(int year, int month, String amount, String status) {
            this.year = year;
            this.month = month;
            this.amount = amount;
            this.status = status;
        }

        public int getYear() {
            return year;
        }

        public int getMonth() {
            return month;
        }

        public String getAmount() {
            return amount;
        }

        public String getStatus() {
            return status;
        }
    }
} 