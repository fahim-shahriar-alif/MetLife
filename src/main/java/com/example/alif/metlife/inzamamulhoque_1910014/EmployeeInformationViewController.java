package com.example.alif.metlife.inzamamulhoque_1910014;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;

public class EmployeeInformationViewController {

    @FXML
    private TextField employeeNameTextField;
    @FXML
    private TextField employeeIDTextField;
    @FXML
    private TextField employeeAddressTextField;
    @FXML
    private TextField employeeContactNoTextField;
    @FXML
    private TextField employeeContractDesignationTextField;
    @FXML
    private TableView<EmployeeModel> employeeInformationsTableView;
    @FXML
    private TableColumn<EmployeeModel, String> employeeNameTableColumn;
    @FXML
    private TableColumn<EmployeeModel, String> employeeIDTableColumn;
    @FXML
    private TableColumn<EmployeeModel, String> employeeContactNoTableColumn;
    @FXML
    private TableColumn<EmployeeModel, String> employeeContractTableColumn;
    @FXML
    private TableColumn<EmployeeModel, String> employeeAddressTableColumn;


    private final ObservableList<EmployeeModel> employeeList = FXCollections.observableArrayList();
    @FXML
    private TextField enterEmployeeNameTextField;
    @FXML
    private Label complainBoxLabel;
    @FXML
    private Tab addNewEmployeeInformationsTab;
    @FXML
    private TextField inputEmployeeNameTextField;
    @FXML
    private TextField enterEmployeeIDTextField;
    @FXML
    private Button submitButtonOnAction;
    @FXML
    private DatePicker employeeJoiningDatePicker;
    @FXML
    private TextField inputEmployeeIDTextField;

    @FXML
    private Tab checkExistentEmployeeInformationsTab;
    @FXML
    private Tab employeeComplaintsTab;

    @FXML
    public void initialize() {
        employeeNameTableColumn.setCellValueFactory(new PropertyValueFactory<EmployeeModel,String>("Employee Name"));
        employeeIDTableColumn.setCellValueFactory(new PropertyValueFactory<EmployeeModel, String>("Employee ID"));
        employeeAddressTableColumn.setCellValueFactory(new PropertyValueFactory<EmployeeModel, String>("Employee Address"));
        employeeContactNoTableColumn.setCellValueFactory(new PropertyValueFactory<EmployeeModel, String>("Employee ContactNo."));
        employeeContractTableColumn.setCellValueFactory(new PropertyValueFactory<EmployeeModel, String>("Employee Contract"));
        employeeList.add(new EmployeeModel("John Doe", "E123","Bashundhara R/A" , "123456789","Full-time"));
        employeeList.add(new EmployeeModel("Jane Smith", "E124","Ghulshan-1" , "987654321","Part-time"));
        employeeInformationsTableView.setItems(employeeList);
    }

    @FXML
    private void showInformationsButtonOnAction(MouseEvent event) {

        String name = employeeNameTextField.getText();
        String id = employeeIDTextField.getText();
        String address= employeeAddressTextField.getText();
        String contactNo = employeeContactNoTextField.getText();
        String contract = employeeContractDesignationTextField.getText();


        employeeList.add(new EmployeeModel(name, id,address, contactNo, contract));


        employeeNameTextField.clear();
        employeeIDTextField.clear();
        employeeAddressTextField.clear();
        employeeContactNoTextField.clear();
        employeeContractDesignationTextField.clear();
    }

    @FXML
    private void backToDashboardButtonOnAction(MouseEvent event) {

        System.out.println("Back to Dashboard clicked");
    }
}
