package com.example.alif.metlife.Alif_2221079;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.*;
import java.time.LocalDate;
import java.util.Random;

public class ClientTrackController {

    @FXML
    private ComboBox<String> contactMethodComboBox;

    @FXML
    private ComboBox<String> priorityLevelComboBox;

    @FXML
    private ComboBox<String> requestTypeComboBox;

    @FXML
    private TextArea detailsTextArea;

    @FXML
    private TableView<ObservableList<String>> requestTableView;

    @FXML
    private TableColumn<ObservableList<String>, String> appointmentDateTableColumn;

    @FXML
    private TableColumn<ObservableList<String>, String> timeSlotTableColumn;

    @FXML
    private TableColumn<ObservableList<String>, String> requestTypeTableColumn;

    @FXML
    private TableColumn<ObservableList<String>, String> contactMethodTableColumn;

    private final String filePath = "support_requests.txt";

    private final String[] timeSlots = {
            "9:00 AM", "10:00 AM", "11:00 AM", "12:00 PM",
            "1:00 PM", "2:00 PM", "3:00 PM", "4:00 PM"
    };

    @FXML
    public void initialize() {
        priorityLevelComboBox.setItems(FXCollections.observableArrayList("High", "Medium", "Low"));
        contactMethodComboBox.setItems(FXCollections.observableArrayList("Email", "Phone", "SMS"));
        requestTypeComboBox.setItems(FXCollections.observableArrayList("Policy Inquiry", "Claim Help", "Payment Issue", "Other"));

        appointmentDateTableColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().get(0)));
        timeSlotTableColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().get(1)));
        requestTypeTableColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().get(2)));
        contactMethodTableColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().get(3)));
    }

    @FXML
    public void submitRequestButtonOnAction(ActionEvent event) {
        String priority = priorityLevelComboBox.getValue();
        String contactMethod = contactMethodComboBox.getValue();
        String requestType = requestTypeComboBox.getValue();
        String details = detailsTextArea.getText();

        if (priority == null || contactMethod == null || requestType == null || details.isEmpty()) {
            showAlert("Error", "Please fill in all fields.");
            return;
        }

        Random random = new Random();
        LocalDate futureDate = LocalDate.now().plusDays(random.nextInt(5) + 1);
        String randomSlot = timeSlots[random.nextInt(timeSlots.length)];

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(futureDate + "," + randomSlot + "," + requestType + "," + contactMethod);
            writer.newLine();
            showAlert("Success", "Support request submitted successfully!");
            clearFields();
        } catch (IOException e) {
            showAlert("Error", "Failed to write to file.");
        }
    }

    @FXML
    public void viewRequestButtonOnAction(ActionEvent event) {
        ObservableList<ObservableList<String>> data = FXCollections.observableArrayList();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length < 4) continue;

                ObservableList<String> row = FXCollections.observableArrayList();
                row.add(parts[0]);
                row.add(parts[1]);
                row.add(parts[2]);
                row.add(parts[3]);

                data.add(row);
            }
            requestTableView.setItems(data);
        } catch (IOException e) {
            showAlert("Error", "Could not load support requests.");
        }
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

    private void clearFields() {
        priorityLevelComboBox.setValue(null);
        contactMethodComboBox.setValue(null);
        requestTypeComboBox.setValue(null);
        detailsTextArea.clear();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
