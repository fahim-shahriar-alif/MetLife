package com.example.alif.metlife.Alif_2221079;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.*;
import java.time.LocalDate;

public class ClientTrackController {
    @FXML public TableView<Appointment> appointmentTableView;
    @FXML public TableColumn<Appointment, String> appointmentDateTableColumn;
    @FXML public TableColumn<Appointment, String> timeSlotTableColumn;
    @FXML public TableColumn<Appointment, String> meetingTypeTableColumn;
    @FXML public TableColumn<Appointment, String> phoneNumberTableColumn;
    @FXML public TableColumn<Appointment, String> meetingNotesTableColumn;
    @FXML public TableColumn<Appointment, String> locationTableColumn;
    @FXML public TableColumn<Appointment, String> statusTableColumn;

    @FXML public ComboBox<String> priorityLevelComboBox;
    @FXML public TextField detailsTextField;

    private final ObservableList<Appointment> appointments = FXCollections.observableArrayList();


    public void initialize() {
        appointmentDateTableColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentDate"));
        timeSlotTableColumn.setCellValueFactory(new PropertyValueFactory<>("timeSlot"));
        meetingTypeTableColumn.setCellValueFactory(new PropertyValueFactory<>("meetingType"));
        phoneNumberTableColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        meetingNotesTableColumn.setCellValueFactory(new PropertyValueFactory<>("meetingNotes"));
        locationTableColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
        statusTableColumn.setCellValueFactory(new PropertyValueFactory<>("status"));


        appointmentTableView.setItems(appointments);
    }

    @FXML
    public void viewAppointmentButtonOnAction(ActionEvent actionEvent) {

        appointments.clear();

        loadAppointments();
    }

    private void loadAppointments() {
        try (BufferedReader reader = new BufferedReader(new FileReader("appointments.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 7) {  // Include status now
                    Appointment appointment = new Appointment(
                            parts[0],
                            parts[1],
                            parts[2],
                            parts[3],
                            parts[4],
                            parts[5],
                            parts[6]
                    );
                    appointments.add(appointment);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "Failed to load appointments.");
        }
    }

    @FXML
    public void requestButtonOnAction(ActionEvent actionEvent) {
        Appointment selectedAppointment = appointmentTableView.getSelectionModel().getSelectedItem();
        if (selectedAppointment == null) {
            showAlert("No Selection", "Please select an appointment first.");
            return;
        }

        String priority = priorityLevelComboBox.getValue();
        String details = detailsTextField.getText().trim();

        if (priority == null || details.isEmpty()) {
            showAlert("Missing Fields", "Please fill in the priority level and supporting details.");
            return;
        }

        saveRequestToFile(selectedAppointment, priority, details);

        priorityLevelComboBox.setValue(null);
        detailsTextField.clear();
    }

    private void saveRequestToFile(Appointment appointment, String priority, String details) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("requests.txt", true))) {
            writer.write(String.join(";",
                    appointment.getAppointmentDate(),
                    appointment.getTimeSlot(),
                    appointment.getMeetingType(),
                    appointment.getPhoneNumber(),
                    appointment.getMeetingNotes(),
                    appointment.getLocation(),
                    appointment.getStatus(),
                    priority,
                    details));
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "Failed to save the request.");
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

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static class Appointment {
        private final String appointmentDate;
        private final String timeSlot;
        private final String meetingType;
        private final String phoneNumber;
        private final String meetingNotes;
        private final String location;
        private String status;

        public Appointment(String appointmentDate, String timeSlot, String meetingType,
                           String phoneNumber, String meetingNotes, String location, String status) {
            this.appointmentDate = appointmentDate;
            this.timeSlot = timeSlot;
            this.meetingType = meetingType;
            this.phoneNumber = phoneNumber;
            this.meetingNotes = meetingNotes;
            this.location = location;
            this.status = status;
        }

        public String getAppointmentDate() { return appointmentDate; }
        public String getTimeSlot() { return timeSlot; }
        public String getMeetingType() { return meetingType; }
        public String getPhoneNumber() { return phoneNumber; }
        public String getMeetingNotes() { return meetingNotes; }
        public String getLocation() { return location; }
        public String getStatus() { return status; }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}
