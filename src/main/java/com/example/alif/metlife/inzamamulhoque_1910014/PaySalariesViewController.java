package com.example.alif.metlife.inzamamulhoque_1910014;

import javafx.event.ActionEvent;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;

import javax.swing.text.TableView;

public class PaySalariesViewController
{
    @javafx.fxml.FXML
    private DatePicker startTimeDatePicker;
    @javafx.fxml.FXML
    private DatePicker endTimeDatePicker;
    @javafx.fxml.FXML
    private TextField employeeNameInputTextField;
    @javafx.fxml.FXML
    private TableColumn<PaySalaries, String> endDateTableColumn;
    @javafx.fxml.FXML
    private TableView trainingWorkshopTableView;
    @javafx.fxml.FXML
    private TableColumn<PaySalaries, String> employeeSalaryTextFieldTableColumn;
    @javafx.fxml.FXML
    private TextField employeeIDInputTextField1;
    @javafx.fxml.FXML
    private TableColumn<PaySalaries, String> employeeNameTableColumn;
    @javafx.fxml.FXML
    private TextField employeeSalaryTextField;
    @javafx.fxml.FXML
    private TableColumn<PaySalaries, String> employeeIDTableColumn;
    @javafx.fxml.FXML
    private TableColumn<PaySalaries, String> startDateTableColumn;

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