package com.example.smarthome3.controllers;

import javafx.collections.FXCollections;
import com.example.smarthome3.Views.AccountType;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SignUpController implements Initializable {
    @FXML
    private ChoiceBox<String> acc_selector;
    @FXML
    private TextField emailField;
    @FXML
    private Button signUpBtn;
    @FXML
    private TextField passwordField;
    @FXML
    private TextField confirmPasswordField;
    @FXML
    private TextField usernameField;
    @FXML
    private Button SignInBtn1;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (acc_selector == null) {
            System.out.println("❌ acc_selector is null! Check your FXML file.");
            return;
        }

        // Populate the ChoiceBox with account types
        acc_selector.setItems(FXCollections.observableArrayList(
                AccountType.HOMEOWNER.name(),
                AccountType.TECHNICIAN.name(),
                AccountType.SECURITYGUARD.name()
        ));

        // Set default value
        acc_selector.setValue(AccountType.HOMEOWNER.name());

        // Set button actions
        if (signUpBtn != null) {
            signUpBtn.setOnAction(event -> onSignup());
        }

        if (SignInBtn1 != null) {
            SignInBtn1.setOnAction(event -> openLoginDashboard());
        }
    }

    private void onSignup() {
        String username = usernameField.getText();
        String password = passwordField.getText();
        String confirmPassword = confirmPasswordField.getText();
        String email = emailField.getText();
        String accountType = acc_selector.getValue();

        // Validate the form fields
        if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || email.isEmpty()) {
            showAlert(AlertType.ERROR, "Form Error", "Please fill all fields.");
            return;
        }

        if (!password.equals(confirmPassword)) {
            showAlert(AlertType.ERROR, "Password Mismatch", "Passwords do not match.");
            return;
        }

        // Assuming the validation passes, show a success alert
        showAlert(AlertType.INFORMATION, "Sign-Up Successful", "You have successfully signed up!");

        // After successful sign-up, navigate to the login page
        openLoginDashboard();
    }

    // Method to show alert dialog boxes
    private void showAlert(AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void openLoginDashboard() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Login.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load()));
            stage.setTitle("Login Dashboard");
            stage.show();

            // Close the SignUp window
            ((Stage) SignInBtn1.getScene().getWindow()).close();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert(AlertType.ERROR, "Error", "Failed to load the login page.");
        }
    }
}
