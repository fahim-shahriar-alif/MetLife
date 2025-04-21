package com.example.alif.metlife.Alif_2221079;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.*;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class ClientProfileController implements Initializable {
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

    private final String fileName = "client_profile.txt"; // save file

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        LocalDate today = LocalDate.now();
        LocalDate maxDate = today.minusYears(18); // Must be 18+

        dobDatePicker.setDayCellFactory((DatePicker picker) -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);

                if (date.isAfter(maxDate)) {
                    setDisable(true);
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

        LocalDate today = LocalDate.now();
        if (dob.isAfter(today.minusYears(18))) {
            showAlert("Invalid DOB", "Client must be at least 18 years old.");
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
            showAlert("Success", "Profile updated successfully.");
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "Failed to save data.");
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
            showAlert("Error", "No profile data found.");
        }
    }

    public void backButtonOnAction(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ClientDash.fxml"));
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
