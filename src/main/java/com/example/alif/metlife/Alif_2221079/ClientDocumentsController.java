package com.example.alif.metlife.Alif_2221079;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class ClientDocumentsController {
    public TextField policyNumberField;
    public Label certificateNameLabel;
    public Label issueDateLabel;
    public Label coverageTypeLabel;
    public Label coverageAmountLabel;
    public Label termLengthLabel;

    private void loadDocumentData(String policyNumber) {

        certificateNameLabel.setText("Life Policy Certificate");
        issueDateLabel.setText("2024-06-15");

        coverageTypeLabel.setText("Term Life Insurance");
        coverageAmountLabel.setText("৳ 5,00,000");
        termLengthLabel.setText("20 Years");
    }

    public void viewCertificateOnAction(ActionEvent actionEvent) {
        String policyNumber = policyNumberField.getText();
        if (policyNumber.isEmpty()) {
            showAlert("Missing Input", "Please enter your Policy Number to view the certificate.");
        } else {
            loadDocumentData(policyNumber);
            showAlert("Success", "Certificate details loaded.");
        }
    }

    public void downloadCertificateOnAction(ActionEvent actionEvent) {
        if (certificateNameLabel.getText().isEmpty()) {
            showAlert("No Data", "Please view the certificate before downloading.");
        } else {
            showAlert("Download", "Certificate downloaded successfully.");
        }
    }

    public void viewCoverageOnAction(ActionEvent actionEvent) {
        if (coverageTypeLabel.getText().isEmpty()) {
            showAlert("No Data", "Please load the certificate first to see coverage details.");
        } else {
            showAlert("Coverage Info", "Coverage details are already shown.");
        }
    }

    public void backButtonOnAction(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ClientDash.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) policyNumberField.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Policy Holder Dashboard");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "Failed to load dashboard.");
        }
    }

    private void showAlert(String title, String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
