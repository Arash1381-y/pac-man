package view.controllers;


import controller.LoginController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class LoginUserView {
    public static boolean firstClickOnUser;
    public static boolean firstClickOnPass;
    public AnchorPane anchorPane;
    public Label error;
    public VBox chart;
    public TextField userPass;
    public TextField username;
    public LoginController controller;
    public ImageView ghost;

    {
        firstClickOnUser = true;
        firstClickOnPass = true;
        controller = new LoginController();
    }

    @FXML
    public void initialize() {
        controller.runGhostTransition(ghost);
    }

    public void switchToWelcomeScene() throws IOException {
        String address = "/userInterface/fxml/Welcome.fxml";
        controller.moveToPage(address, username, "welcome");
    }


    public void logInUser() throws IOException {
        String name;
        String password;
        name = username.getText();
        password = userPass.getText();
        controller.loginUser(name, password, error, username, userPass);
    }

    public void scaredGhost() {
        controller.changePic(ghost);
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
