package com.example.smarthome3.controllers.Homeowner;

import com.example.smarthome3.Models.Model;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import javafx.scene.Node;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeownerController implements Initializable {
    @FXML
    private BorderPane homeowner_parent;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Initializing HomeownerController...");

        if (homeowner_parent == null) {
            System.out.println("homeowner_parent is null.");
        } else {
            System.out.println("homeowner_parent is initialized.");
        }

        // This part should be outside of any if check related to the parent being null
        Model.getInstance().getViewFactory().getHomeownerSelectedMenuItem().addListener(((observableValue, oldVal, newVal) -> {
            System.out.println("Switching to: " + newVal);

            // Switch logic for different views based on newVal
            Node view = switch (newVal) {
                case "Humidity" -> Model.getInstance().getViewFactory().getHumidityView();
                case "Temperature" -> Model.getInstance().getViewFactory().getTemperatureView();
                case "Motion" -> Model.getInstance().getViewFactory().getMotionView();
                case "Light" -> Model.getInstance().getViewFactory().getLightView();
                default -> Model.getInstance().getViewFactory().getDashboardView();
            };

            if (view != null) {
                if (homeowner_parent != null) {
                    homeowner_parent.setCenter(view);
                    System.out.println(newVal + " view is now displayed.");
                } else {
                    System.out.println("homeowner_parent is null, cannot set center.");
                }
            } else {
                System.out.println("View is NULL for: " + newVal);
            }
        }));
    }
}