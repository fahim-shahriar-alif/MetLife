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
import javafx.scene.control.*;
import javafx.scene.Node;
import javafx.stage.Stage;

import java.io.IOException;

public class ClientBrowseController {

    @FXML
    private TableView<Plan> planTableView;

    @FXML
    private TableColumn<Plan, String> planNameColumn;

    @FXML
    private TableColumn<Plan, String> premiumColumnView;

    @FXML
    private TableColumn<Plan, String> coverageColumnView;

    @FXML
    private TableColumn<Plan, String> termColumnView;

    private final ObservableList<Plan> planList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        planList.addAll(
                new Plan("Basic Plan", "500 BDT", "2,00,000 BDT", "10 years"),
                new Plan("Standard Plan", "800 BDT", "5,00,000 BDT", "15 years"),
                new Plan("Premium Plan", "1200 BDT", "10,00,000 BDT", "20 years")
        );

        planNameColumn.setCellValueFactory(cellData -> cellData.getValue().planNameProperty());
        premiumColumnView.setCellValueFactory(cellData -> cellData.getValue().premiumProperty());
        coverageColumnView.setCellValueFactory(cellData -> cellData.getValue().coverageProperty());
        termColumnView.setCellValueFactory(cellData -> cellData.getValue().termProperty());

        planTableView.setItems(planList);
        planTableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }

    @FXML
    public void purchaseButtonOnAction(ActionEvent event) {
        Plan selectedPlan = planTableView.getSelectionModel().getSelectedItem();

        if (selectedPlan == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText(null);
            alert.setContentText("Please select a plan before purchasing.");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Purchase Successful");
            alert.setHeaderText(null);
            alert.setContentText("You have successfully purchased: " + selectedPlan.getPlanName());
            alert.showAndWait();
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

    // Inner class Plan
    public static class Plan {
        private final StringProperty planName;
        private final StringProperty premium;
        private final StringProperty coverage;
        private final StringProperty term;

        public Plan(String planName, String premium, String coverage, String term) {
            this.planName = new SimpleStringProperty(planName);
            this.premium = new SimpleStringProperty(premium);
            this.coverage = new SimpleStringProperty(coverage);
            this.term = new SimpleStringProperty(term);
        }

        public String getPlanName() {
            return planName.get();
        }

        public StringProperty planNameProperty() {
            return planName;
        }

        public String getPremium() {
            return premium.get();
        }

        public StringProperty premiumProperty() {
            return premium;
        }

        public String getCoverage() {
            return coverage.get();
        }

        public StringProperty coverageProperty() {
            return coverage;
        }

        public String getTerm() {
            return term.get();
        }

        public StringProperty termProperty() {
            return term;
        }
    }
}
