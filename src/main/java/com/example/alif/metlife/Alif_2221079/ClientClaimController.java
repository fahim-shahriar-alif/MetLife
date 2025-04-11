package com.example.alif.metlife.Alif_2221079;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;

import java.io.IOException;

public class ClientClaimController {

    @FXML
    private ComboBox<String> claimTypeComboBox;

    @FXML
    private TextField phoneNumberTextField;

    @FXML
    private TextArea detailsTextArea;

    @FXML
    public void initialize() {

        claimTypeComboBox.getItems().addAll(
                "Death Claim",
                "Maturity Claim",
                "Accidental Disability Claim",
                "Critical Illness Claim",
                "Hospitalization Claim"
        );
    }

    @FXML
    public void claimButtonOnAction(ActionEvent event) {
        String claimType = claimTypeComboBox.getValue();
        String phone = phoneNumberTextField.getText();
        String details = detailsTextArea.getText();

        if (claimType == null || phone.isEmpty() || details.isEmpty()) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Incomplete Submission");
            alert.setHeaderText("Missing Information");
            alert.setContentText("Please fill all fields before submitting the claim.");
            alert.showAndWait();
            return;
        }

        Alert successAlert = new Alert(AlertType.INFORMATION);
        successAlert.setTitle("Claim Submitted");
        successAlert.setHeaderText("Submission Successful");
        successAlert.setContentText("Your claim has been successfully submitted.");
        successAlert.showAndWait();

        claimTypeComboBox.setValue(null);
        phoneNumberTextField.clear();
        detailsTextArea.clear();
    }

    @FXML
    public void backButtonOnAction(ActionEvent event) {
        try {
            Parent dashboardRoot = FXMLLoader.load(getClass().getResource("ClientDash.fxml"));
            Stage stage = (Stage) claimTypeComboBox.getScene().getWindow();
            stage.setScene(new Scene(dashboardRoot));
            stage.setTitle("Client Dashboard");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
