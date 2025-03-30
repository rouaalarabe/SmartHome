package com.example.smarthome3.controllers;
import javafx.collections.FXCollections;

import com.example.smarthome3.Models.Model;
import com.example.smarthome3.Views.AccountType;
import javafx.collections.FXCollections;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML
    public ChoiceBox<AccountType> acc_selector;
    @FXML
    public TextField user_address_lbl;
    @FXML
    public Button login_btn;
    @FXML
    public TextField password_fld;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        acc_selector.setItems(FXCollections.observableArrayList(AccountType.HOMEOWNER,AccountType.TECHNICIAN,AccountType.SECURITYGUARD));
        acc_selector.setValue(Model.getInstance().getViewFactory().getLoginAccountType());
        acc_selector.valueProperty().addListener(observable -> Model.getInstance().getViewFactory().setLoginAccountType(acc_selector.getValue()));
        login_btn.setOnAction(event -> onLogin());
    }

    private void onLogin() {

        Stage stage = (Stage) login_btn.getScene().getWindow();

            Model.getInstance().getViewFactory().closeStage(stage);
        AccountType accountType = Model.getInstance().getViewFactory().getLoginAccountType();

        try {
            if (accountType == AccountType.HOMEOWNER) {
                Model.getInstance().getViewFactory().showHomeownerWindow();
            } else if (accountType == AccountType.TECHNICIAN) {
                Model.getInstance().getViewFactory().showTechnicianWindow();
            } else if (accountType == AccountType.SECURITYGUARD) {
                Model.getInstance().getViewFactory().showSecurityGuardWindow();
            } else {
                System.out.println("❌ Error: Unknown account type selected.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(" Error while loading the window: " + e.getMessage());
        }
    }
        }