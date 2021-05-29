package view.controllers;

import controller.PassChangeController;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import model.LoginUser;

import java.io.IOException;

public class ChangePassView {
    public PasswordField oldPass;
    public TextField newPass;
    public Label error;
    public AnchorPane anchorPane;
    PassChangeController controller;

    {
        controller = new PassChangeController();
    }

    public void backToProfile(ActionEvent event) throws IOException {
        String address = "/userInterface/fxml/Profile.fxml";
        controller.moveToPage(address, error, "profile");
    }

    public void changePass() throws IOException {
        controller.apply(error, oldPass.getText(), newPass.getText(), LoginUser.getPlayer());
    }
}
