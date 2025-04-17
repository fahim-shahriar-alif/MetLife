package com.example.alif.metlife.Alif_2221079;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.stage.Stage;
import javafx.scene.control.*;

import java.io.IOException;
import java.util.Optional;

public class AgentClaimsManagementController {

    @FXML
    private TableView<Claim> claimsTableView;

    @FXML
    private TableColumn<Claim, String> claimIdColumn;

    @FXML
    private TableColumn<Claim, String> clientNameColumn;

    @FXML
    private TableColumn<Claim, String> policyTypeColumn;

    @FXML
    private TableColumn<Claim, String> claimAmountColumn;

    @FXML
    private TableColumn<Claim, String> statusColumn;

    @FXML
    private TableColumn<Claim, String> actionsColumn;

    private final ObservableList<Claim> claimsList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        claimsList.addAll(
            new Claim("CL001", "John Doe", "Life Insurance", "50000", "Pending"),
            new Claim("CL002", "Jane Smith", "Health Insurance", "25000", "Processing")
        );

        claimIdColumn.setCellValueFactory(cellData -> cellData.getValue().claimIdProperty());
        clientNameColumn.setCellValueFactory(cellData -> cellData.getValue().clientNameProperty());
        policyTypeColumn.setCellValueFactory(cellData -> cellData.getValue().policyTypeProperty());
        claimAmountColumn.setCellValueFactory(cellData -> cellData.getValue().claimAmountProperty());
        statusColumn.setCellValueFactory(cellData -> cellData.getValue().statusProperty());

        claimsTableView.setItems(claimsList);
        claimsTableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }

    @FXML
    public void newClaimButtonOnAction(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("NewClaim.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("New Claim");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void processClaimButtonOnAction(ActionEvent event) {
        Claim selectedClaim = claimsTableView.getSelectionModel().getSelectedItem();
        if (selectedClaim == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText(null);
            alert.setContentText("Please select a claim to process.");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Process Claim");
            alert.setHeaderText("Process Claim #" + selectedClaim.getClaimId());
            alert.setContentText("Are you sure you want to process this claim?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                selectedClaim.setStatus("Processed");
                claimsTableView.refresh();
                
                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                successAlert.setTitle("Success");
                successAlert.setHeaderText(null);
                successAlert.setContentText("Claim processed successfully.");
                successAlert.showAndWait();
            }
        }
    }

    @FXML
    public void backButtonOnAction(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AgentDash.fxml"));
            Parent root = fxmlLoader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Agent Dashboard");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static class Claim {
        private final StringProperty claimId;
        private final StringProperty clientName;
        private final StringProperty policyType;
        private final StringProperty claimAmount;
        private final StringProperty status;

        public Claim(String claimId, String clientName, String policyType, String claimAmount, String status) {
            this.claimId = new SimpleStringProperty(claimId);
            this.clientName = new SimpleStringProperty(clientName);
            this.policyType = new SimpleStringProperty(policyType);
            this.claimAmount = new SimpleStringProperty(claimAmount);
            this.status = new SimpleStringProperty(status);
        }

        public String getClaimId() {
            return claimId.get();
        }

        public StringProperty claimIdProperty() {
            return claimId;
        }

        public String getClientName() {
            return clientName.get();
        }

        public StringProperty clientNameProperty() {
            return clientName;
        }

        public String getPolicyType() {
            return policyType.get();
        }

        public StringProperty policyTypeProperty() {
            return policyType;
        }

        public String getClaimAmount() {
            return claimAmount.get();
        }

        public StringProperty claimAmountProperty() {
            return claimAmount;
        }

        public String getStatus() {
            return status.get();
        }

        public StringProperty statusProperty() {
            return status;
        }

        public void setStatus(String status) {
            this.status.set(status);
        }
    }
}
