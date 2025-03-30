package com.example.smarthome3.Models;

import com.example.smarthome3.Views.ViewFactory;
import com.example.smarthome3.Views.AccountType; // Import AccountType enum

public class Model {
    private static Model model;
    private final ViewFactory viewFactory;
    private AccountType userRole; // ✅ Add userRole variable

    private Model() {
        this.viewFactory = new ViewFactory();
    }

    public static synchronized Model getInstance() {
        if (model == null) {
            model = new Model();
        }
        return model;
    }

    public ViewFactory getViewFactory() {
        return viewFactory;
    }

    // ✅ Getter for userRole
    public AccountType getUserRole() {
        return userRole;
    }

    // ✅ Setter for userRole
    public void setUserRole(AccountType userRole) {
        this.userRole = userRole;
    }
    // Method to handle user registration
    public boolean registerUser(String email, String username, String password, String confirmPassword, AccountType userRole) {
        // Simple validation for registration
        if (email.isEmpty() || username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || userRole == null) {
            return false; // Registration fails if any field is empty
        }

        if (!password.equals(confirmPassword)) {
            return false; // Registration fails if passwords don't match
        }



        // Here you can add logic to save the user data to a database or storage system

        return true; // Registration success
    }
}
