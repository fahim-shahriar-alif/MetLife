package com.example.alif.metlife.Alif_2221079;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import java.util.Optional;
import java.util.stream.IntStream;

public class ClientPaymentController {
    public TextField policyNumberTextField;
    public TextField premiumAmountTextField;
    public ComboBox<String> selectMonthComboBox;
    public ComboBox<String> selectYearComboBox;

    public void initialize() {
        LocalDate now = LocalDate.now();
        int currentYear = now.getYear();
        Month currentMonth = now.getMonth();

        IntStream.range(currentYear, currentYear + 5)
                .forEach(year -> selectYearComboBox.getItems().add(String.valueOf(year)));


        selectYearComboBox.setOnAction(e -> {
            selectMonthComboBox.getItems().clear();
            int selectedYear = Integer.parseInt(selectYearComboBox.getValue());
            Month[] months = Month.values();
            for (Month month : months) {
                if (selectedYear > currentYear || month.getValue() >= currentMonth.getValue()) {
                    selectMonthComboBox.getItems().add(month.name());
                }
            }
        });
    }


    public void setPlanDetails(ClientBrowseController.Plan selectedPlan) {

        policyNumberTextField.setText("Policy #" + selectedPlan.getPlanName());
        premiumAmountTextField.setText(selectedPlan.getPremium());
    }

    public void payButtonOnAction(ActionEvent actionEvent) {
        String policyNumber = policyNumberTextField.getText();
        String premium = premiumAmountTextField.getText();
        String selectedMonth = selectMonthComboBox.getValue();
        String selectedYear = selectYearComboBox.getValue();

        if (policyNumber.isEmpty() || premium.isEmpty() || selectedMonth == null || selectedYear == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Incomplete Information");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all the fields before proceeding.");
            alert.showAndWait();
            return;
        }

        Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmAlert.setTitle("Confirm Payment");
        confirmAlert.setHeaderText("Please confirm your payment details");
        confirmAlert.setContentText("Policy Number: " + policyNumber +
                "\nPremium Amount: " + premium +
                "\nMonth: " + selectedMonth +
                "\nYear: " + selectedYear);

        ButtonType confirm = new ButtonType("Confirm");
        ButtonType cancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        confirmAlert.getButtonTypes().setAll(confirm, cancel);

        Optional<ButtonType> result = confirmAlert.showAndWait();

        if (result.isPresent() && result.get() == confirm) {
            Alert success = new Alert(Alert.AlertType.INFORMATION);
            success.setTitle("Payment Status");
            success.setHeaderText(null);
            success.setContentText("Payment Successful");
            success.showAndWait();
        } else {
            Alert cancelled = new Alert(Alert.AlertType.INFORMATION);
            cancelled.setTitle("Payment Status");
            cancelled.setHeaderText(null);
            cancelled.setContentText("Cancelled");
            cancelled.showAndWait();
        }
    }

    public void backButtonOnAction(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ClientDash.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Policy Holder Dashboard");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
