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

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ClientBrowseController {

    @FXML
    private TableView<Plan> planTableView;

    @FXML
    private TableColumn<Plan, String> planIDColumn;

    @FXML
    private TableColumn<Plan, String> planNameColumn;

    @FXML
    private TableColumn<Plan, String> premiumColumnView;

    @FXML
    private TableColumn<Plan, String> coverageColumnView;

    @FXML
    private TableColumn<Plan, String> termColumnView;

    private final ObservableList<Plan> planList = FXCollections.observableArrayList();
    private static final String PURCHASED_PLANS_FILE = "purchased_plans.txt";

    @FXML
    public void initialize() {
        planList.addAll(
                new Plan("P001", "Basic Plan", "500", "2,00,000", "10 years"),
                new Plan("P002", "Standard Plan", "800", "5,00,000", "15 years"),
                new Plan("P003", "Premium Plan", "1200", "10,00,000", "20 years")
        );

        planIDColumn.setCellValueFactory(cellData -> cellData.getValue().planIDProperty());
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
            try {
                List<PurchasedPlan> purchasedPlans = loadPurchasedPlans();
                
                if (purchasedPlans.stream().anyMatch(p -> p.getPlanID().equals(selectedPlan.getPlanID()))) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Already Purchased");
                    alert.setHeaderText(null);
                    alert.setContentText("You have already purchased this plan.");
                    alert.showAndWait();
                    return;
                }

                PurchasedPlan newPlan = new PurchasedPlan(
                    selectedPlan.getPlanID(),
                    selectedPlan.getPlanName(),
                    selectedPlan.getPremium(),
                    selectedPlan.getCoverage(),
                    selectedPlan.getTerm()
                );
                
                purchasedPlans.add(newPlan);
                savePurchasedPlans(purchasedPlans);

                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                successAlert.setTitle("Purchase Successful");
                successAlert.setHeaderText(null);
                successAlert.setContentText("You have successfully purchased the " + selectedPlan.getPlanName() + " plan.");
                successAlert.showAndWait();
            } catch (IOException e) {
                e.printStackTrace();
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setTitle("Error");
                errorAlert.setHeaderText(null);
                errorAlert.setContentText("Error saving purchased plan: " + e.getMessage());
                errorAlert.showAndWait();
            }
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

    private List<PurchasedPlan> loadPurchasedPlans() throws IOException {
        List<PurchasedPlan> plans = new ArrayList<>();
        File file = new File(PURCHASED_PLANS_FILE);
        
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split("\\|");
                    if (parts.length >= 5) {
                        PurchasedPlan plan = new PurchasedPlan(
                            parts[0],
                            parts[1],
                            parts[2],
                            parts[3],
                            parts[4]
                        );
                        plans.add(plan);
                    }
                }
            }
        }
        
        return plans;
    }

    private void savePurchasedPlans(List<PurchasedPlan> plans) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(PURCHASED_PLANS_FILE))) {
            for (PurchasedPlan plan : plans) {
                writer.write(plan.getPlanID() + "|" +
                           plan.getPlanName() + "|" +
                           plan.getPremium() + "|" +
                           plan.getCoverage() + "|" +
                           plan.getTerm());
                writer.newLine();
            }
        }
    }

    public static class Plan {
        private final StringProperty planID;
        private final StringProperty planName;
        private final StringProperty premium;
        private final StringProperty coverage;
        private final StringProperty term;

        public Plan(String planID, String planName, String premium, String coverage, String term) {
            this.planID = new SimpleStringProperty(planID);
            this.planName = new SimpleStringProperty(planName);
            this.premium = new SimpleStringProperty(premium);
            this.coverage = new SimpleStringProperty(coverage);
            this.term = new SimpleStringProperty(term);
        }

        public String getPlanID() {
            return planID.get();
        }

        public StringProperty planIDProperty() {
            return planID;
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
