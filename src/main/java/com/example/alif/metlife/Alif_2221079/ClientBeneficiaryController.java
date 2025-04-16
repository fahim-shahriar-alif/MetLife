package com.example.alif.metlife.Alif_2221079;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class ClientBeneficiaryController implements Initializable {
    public TextField nameTextField;
    public TextField nidTextField;
    public DatePicker dobDatePicker;
    public TextField phoneNumberTextField;
    public TextField emailTextField;
    public TextField addressTextField;

    public Label nameViewLabel;
    public Label nidViewLabel;
    public Label dobViewLabel;
    public Label numberViewLabel;
    public Label emailViewLabel;
    public Label addressViewLabel;

    private final String fileName = "beneficiary_profile.txt"; // separate file

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        LocalDate today = LocalDate.now();
        LocalDate maxDate = today.minusYears(18); // 18+ required

        dobDatePicker.setDayCellFactory((DatePicker picker) -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                if (date.isAfter(maxDate)) {
                    setDisable(true);
                    setStyle("-fx-background-color: #ffc0cb;");
                }
            }
        });

        dobDatePicker.setPromptText("Select DOB (18+ only)");
    }

    public void updateButtonOnAction(ActionEvent actionEvent) {
        String name = nameTextField.getText();
        String nid = nidTextField.getText();
        LocalDate dob = dobDatePicker.getValue();
        String phone = phoneNumberTextField.getText();
        String email = emailTextField.getText();
        String address = addressTextField.getText();

        if (name.isEmpty() || nid.isEmpty() || dob == null || phone.isEmpty() || email.isEmpty() || address.isEmpty()) {
            showAlert("Input Error", "Please fill out all fields.");
            return;
        }

        if (!phone.matches("\\d+")) {
            showAlert("Invalid Phone", "Phone number must contain only digits.");
            return;
        }

        if (dob.isAfter(LocalDate.now().minusYears(18))) {
            showAlert("Invalid DOB", "Beneficiary must be at least 18 years old.");
            return;
        }

        String birthYear = String.valueOf(dob.getYear());
        if (!nid.startsWith(birthYear)) {
            showAlert("Invalid NID", "NID must start with birth year: " + birthYear);
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(name);
            writer.newLine();
            writer.write(nid);
            writer.newLine();
            writer.write(dob.toString());
            writer.newLine();
            writer.write(phone);
            writer.newLine();
            writer.write(email);
            writer.newLine();
            writer.write(address);
            showAlert("Success", "Beneficiary profile updated.");
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "Failed to save beneficiary profile.");
        }
    }

    public void viewProfileButtonOnAction(ActionEvent actionEvent) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            nameViewLabel.setText(reader.readLine());
            nidViewLabel.setText(reader.readLine());
            dobViewLabel.setText(reader.readLine());
            numberViewLabel.setText(reader.readLine());
            emailViewLabel.setText(reader.readLine());
            addressViewLabel.setText(reader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "No saved beneficiary profile found.");
        }
    }

    public void backButtonOnAction(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ClientDash.fxml")); // Change if needed
            Parent root = loader.load();
            Stage stage = (Stage) nameTextField.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Policy Holder Dashboard");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "Failed to load dashboard.");
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
