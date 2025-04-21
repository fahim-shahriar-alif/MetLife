package com.example.alif.metlife.inzamamulhoque_1910014;

import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;


public class JobRecruitmentViewController
{
    @javafx.fxml.FXML
    private TextField jobRequirementTextField;
    @javafx.fxml.FXML
    private TableColumn<EmployeeJob,String> jobDescriptionTableCloumn;
    @javafx.fxml.FXML
    private TextField jobPositionTextField;
    @javafx.fxml.FXML
    private TableView<EmployeeJob> jobRecruitmentTableView;
    @javafx.fxml.FXML
    private TextField jobDescriptionTextField;
    @javafx.fxml.FXML
    private TableColumn<EmployeeJob,String> jobPositionTableCloumn;
    @javafx.fxml.FXML
    private TableColumn<EmployeeJob,String> salaryRangeTableColumn;
    @javafx.fxml.FXML
    private TextField jobSalaryTextField;
    @javafx.fxml.FXML
    private TableColumn<EmployeeJob,String> jobRequirementTableCloumn;

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