package com.example.alif.metlife;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {
    @FXML
    public TextField usernameTextField;
    @FXML
    public TextField passwordTextField;
    @FXML
    public Label messageLabel;


    @FXML
    public void loginButtonOnAction(ActionEvent actionEvent) throws IOException {
        String username = usernameTextField.getText();
        String password = passwordTextField.getText();

        if (username.equals("Client") && password.equals("alif")) {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Alif_2221079/ClientDash.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) usernameTextField.getScene().getWindow();
            stage.setTitle("Client Dash");
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }

        else if (username.equals("Agent") && password.equals("alif")) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Alif_2221079/ClientDash.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) usernameTextField.getScene().getWindow();
            stage.setTitle("Agent Dash");
            Scene scene = new Scene(root);
            stage.show();

        }else {
            messageLabel.setText("Invalid Username or Password. Please try again.");
        }


    }
}