package com.example.alif.metlife.Alif_2221079;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.scene.Node;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

public class ClientPaymentController {

    @FXML
    private TextField policyNumberTextField;

    @FXML
    private TextField premiumAmountTextField;

    @FXML
    private ComboBox<String> selectMonthComboBox;

    @FXML
    private ComboBox<String> selectYearComboBox;

    private List<PurchasedPlan> purchasedPlans;

    @FXML
    public void initialize() {
        String[] months = {"January", "February", "March", "April", "May", "June", 
                          "July", "August", "September", "October", "November", "December"};
        selectMonthComboBox.getItems().addAll(months);
        
        int currentYear = LocalDate.now().getYear();
        selectYearComboBox.getItems().addAll(
            String.valueOf(currentYear),
            String.valueOf(currentYear + 1)
        );
        
        loadPurchasedPlans();
    }

    @FXML
    public void loadButtonOnAction(ActionEvent event) {
        String policyId = policyNumberTextField.getText().trim();
        if (policyId.isEmpty()) {
            showAlert("Error", "Please enter a policy ID");
            return;
        }

        PurchasedPlan plan = purchasedPlans.stream()
            .filter(p -> p.getPlanID().equals(policyId))
            .findFirst()
            .orElse(null);

        if (plan == null) {
            showAlert("Error", "No plan found with the given policy ID");
            return;
        }

        premiumAmountTextField.setText(plan.getPremium());
    }

    @FXML
    public void payButtonOnAction(ActionEvent event) {
        String policyId = policyNumberTextField.getText().trim();
        String premiumAmount = premiumAmountTextField.getText().trim();
        String selectedMonth = selectMonthComboBox.getValue();
        String selectedYear = selectYearComboBox.getValue();

        if (policyId.isEmpty() || premiumAmount.isEmpty() || 
            selectedMonth == null || selectedYear == null) {
            showAlert("Error", "Please fill in all fields");
            return;
        }

        PurchasedPlan plan = purchasedPlans.stream()
            .filter(p -> p.getPlanID().equals(policyId))
            .findFirst()
            .orElse(null);

        if (plan == null) {
            showAlert("Error", "Invalid policy ID");
            return;
        }

        int month = getMonthNumber(selectedMonth);
        int year = Integer.parseInt(selectedYear);
        
        if (plan.isMonthPaid(year, month)) {
            showAlert("Error", "This month has already been paid");
            return;
        }

        plan.addPayment(new PurchasedPlan.PaymentRecord(
            year, month, premiumAmount, "Paid"
        ));

        savePurchasedPlans();

        showAlert("Success", "Payment successful for " + selectedMonth + " " + selectedYear);
    }

    @FXML
    public void backButtonOnAction(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ClientDash.fxml"));
            Parent root = fxmlLoader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Client Dashboard");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadPurchasedPlans() {
        purchasedPlans = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("purchased_plans.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length >= 5) {
                    PurchasedPlan plan = new PurchasedPlan(
                        parts[0],
                        parts[1],
                        parts[2],
                        parts[3],
                        parts[4]
                    );
                    purchasedPlans.add(plan);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void savePurchasedPlans() {
        try (java.io.BufferedWriter writer = new java.io.BufferedWriter(new java.io.FileWriter("purchased_plans.txt"))) {
            for (PurchasedPlan plan : purchasedPlans) {
                writer.write(plan.getPlanID() + "|" +
                           plan.getPlanName() + "|" +
                           plan.getPremium() + "|" +
                           plan.getCoverage() + "|" +
                           plan.getTerm());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int getMonthNumber(String monthName) {
        String[] months = {"January", "February", "March", "April", "May", "June", 
                          "July", "August", "September", "October", "November", "December"};
        for (int i = 0; i < months.length; i++) {
            if (months[i].equals(monthName)) {
                return i + 1;
            }
        }
        return -1;
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
