package com.example.alif.metlife.inzamamulhoque_1910014;

import javafx.event.ActionEvent;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;

import javax.swing.text.TableView;

public class TrainingWorkshopViewController
{
    @javafx.fxml.FXML
    private DatePicker startDatePicker;
    @javafx.fxml.FXML
    private DatePicker endDatePicker;
    @javafx.fxml.FXML
    private TableColumn<TrainingWorkshop,String> employeeNameTableColumn;
    @javafx.fxml.FXML
    private TextField employeeNameInputTextField;
    @javafx.fxml.FXML
    private TableColumn<TrainingWorkshop,String> employeeIDTableColumn1;
    @javafx.fxml.FXML
    private TableColumn<TrainingWorkshop,String> employeeIDTableColumn;
    @javafx.fxml.FXML
    private TableColumn<TrainingWorkshop,String> salaryRangeTableColumn;
    @javafx.fxml.FXML
    private TextField employeeIDInputTextField;
    @javafx.fxml.FXML
    private TextField trainingOrWorkshopContentTextField;
    @javafx.fxml.FXML
    private TableView trainingWorkshopTableView;
    @javafx.fxml.FXML
    private TableColumn<TrainingWorkshop,String> jobRequirementTableColumn;

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void backToDashboardButtonOnAction(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void showInformationsButtonOnAction(ActionEvent actionEvent) {
    }
}