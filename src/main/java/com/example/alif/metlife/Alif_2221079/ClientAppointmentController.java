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

public class ClientAppointmentController {
    @FXML public DatePicker appointmentDatePicker;
    @FXML public TextField phoneNumberTextField;
    @FXML public TextField meetingNotesTextField;
    @FXML public TextField locationTextField;
    @FXML public ComboBox<String> timeSlotComboBox;
    @FXML public ComboBox<String> meetingTypeComboBox;
    @FXML public TableView<Appointment> appointmentTableView;
    @FXML public TableColumn<Appointment, String> appointmentDateTableColumn;
    @FXML public TableColumn<Appointment, String> timeSlotTableColumn;
    @FXML public TableColumn<Appointment, String> meetingTypeTableColumn;
    @FXML public TableColumn<Appointment, String> phoneNumberTableColumn;
    @FXML public TableColumn<Appointment, String> meetingNotesTableColumn;
    @FXML public TableColumn<Appointment, String> locationTableColumn;

    private final ObservableList<Appointment> appointments = FXCollections.observableArrayList();

    public void initialize() {

        appointmentDatePicker.setDayCellFactory(getDateCellFactory());

        timeSlotComboBox.setItems(FXCollections.observableArrayList(
                "10:00 AM", "11:00 AM", "12:00 PM", "2:00 PM", "3:00 PM", "4:00 PM"
        ));
        meetingTypeComboBox.setItems(FXCollections.observableArrayList(
                "In-Person", "Virtual"
        ));

        appointmentDateTableColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentDate"));
        timeSlotTableColumn.setCellValueFactory(new PropertyValueFactory<>("timeSlot"));
        meetingTypeTableColumn.setCellValueFactory(new PropertyValueFactory<>("meetingType"));
        phoneNumberTableColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        meetingNotesTableColumn.setCellValueFactory(new PropertyValueFactory<>("meetingNotes"));
        locationTableColumn.setCellValueFactory(new PropertyValueFactory<>("location"));

        appointmentTableView.setItems(appointments);
    }

    private Callback<DatePicker, DateCell> getDateCellFactory() {
        return datePicker -> new DateCell() {
            @Override
            public void updateItem(LocalDate item, boolean empty) {
                super.updateItem(item, empty);
                if (item.isBefore(LocalDate.now().plusDays(1))) {
                    setDisable(true);
                    setStyle("-fx-background-color: #ffc0cb;");
                }
            }
        };
    }

    @FXML
    public void addAppointmentButtonOnAction(ActionEvent actionEvent) {
        LocalDate date = appointmentDatePicker.getValue();
        String timeSlot = timeSlotComboBox.getValue();
        String meetingType = meetingTypeComboBox.getValue();
        String phone = phoneNumberTextField.getText().trim();
        String notes = meetingNotesTextField.getText().trim();
        String location = locationTextField.getText().trim();

        if (date == null || timeSlot == null || meetingType == null || phone.isEmpty() || location.isEmpty()) {
            showAlert("Missing Fields", "Please fill all required fields.");
            return;
        }

        if (!date.isAfter(LocalDate.now())) {
            showAlert("Invalid Date", "Appointment date must be in the future.");
            return;
        }

        Appointment appointment = new Appointment(date.toString(), timeSlot, meetingType, phone, notes, location);
        appointments.add(appointment);
        saveToFile(appointment);
        clearFields();
    }

    @FXML
    public void viewAppointmentButtonOnAction(ActionEvent actionEvent) {
        appointments.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader("appointments.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 6) {
                    Appointment appointment = new Appointment(
                            parts[0], parts[1], parts[2], parts[3], parts[4], parts[5]
                    );
                    appointments.add(appointment);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
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

    private void clearFields() {
        appointmentDatePicker.setValue(null);
        timeSlotComboBox.setValue(null);
        meetingTypeComboBox.setValue(null);
        phoneNumberTextField.clear();
        meetingNotesTextField.clear();
        locationTextField.clear();
    }

    public static class Appointment {
        private final String appointmentDate;
        private final String timeSlot;
        private final String meetingType;
        private final String phoneNumber;
        private final String meetingNotes;
        private final String location;

        public Appointment(String appointmentDate, String timeSlot, String meetingType,
                           String phoneNumber, String meetingNotes, String location) {
            this.appointmentDate = appointmentDate;
            this.timeSlot = timeSlot;
            this.meetingType = meetingType;
            this.phoneNumber = phoneNumber;
            this.meetingNotes = meetingNotes;
            this.location = location;
        }

        public String getAppointmentDate() { return appointmentDate; }
        public String getTimeSlot() { return timeSlot; }
        public String getMeetingType() { return meetingType; }
        public String getPhoneNumber() { return phoneNumber; }
        public String getMeetingNotes() { return meetingNotes; }
        public String getLocation() { return location; }
    }

    private void saveToFile(Appointment appointment) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("appointments.txt", true))) {
            writer.write(String.join(";",
                    appointment.getAppointmentDate(),
                    appointment.getTimeSlot(),
                    appointment.getMeetingType(),
                    appointment.getPhoneNumber(),
                    appointment.getMeetingNotes(),
                    appointment.getLocation()));
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
