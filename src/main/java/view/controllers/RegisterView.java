package view.controllers;


import controller.RegisterController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.Objects;

public class RegisterView {
    public static boolean firstClickOnUser;
    public static boolean firstClickOnPass;
    public PasswordField userPass;
    public TextField username;
    public Label error;
    public AnchorPane anchorPane;
    RegisterController controller;
    private Parent parent;

    {
        firstClickOnUser = true;
        firstClickOnPass = true;
        controller = new RegisterController();
    }

    {
        try {
            parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/userInterface/fxml/Welcome.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void switchToWelcomeScene() throws IOException {
        String address = "/userInterface/fxml/Welcome.fxml";
        controller.moveToPage(address , username, "welcome");
    }

    public void makeNewUser() throws IOException {
        String name;
        String password;
        name = username.getText();
        password = userPass.getText();
        controller.register(name, error, password, username, userPass);
    }


    public void clearUsername() {
        if (firstClickOnUser) {
            username.clear();
            firstClickOnUser = false;
        }
    }


    public void clearPass() {
        if (firstClickOnPass) {
            userPass.clear();
            firstClickOnPass = false;
        }
    }

}
