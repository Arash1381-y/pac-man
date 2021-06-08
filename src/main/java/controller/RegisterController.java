package controller;

import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.Player;

import java.io.IOException;


public class RegisterController extends Controller {

    public void register(String name, Label error, String password, TextField username, TextField userPass) throws IOException {
        if (username.getText().equals("user name") || userPass.getText().equals("password")) {
            Alert errorAlert=new Alert(Alert.AlertType.ERROR);
            errorAlert.setContentText("you cant use this name as your username or password");
            errorAlert.show();
        } else {
            Player player = Player.getPlayerByName(name);
            if (player != null) {
                Alert errorAlert=new Alert(Alert.AlertType.ERROR);
                errorAlert.setContentText("this username is used before");
                errorAlert.show();
                username.clear();
                userPass.clear();
                return;
            }
            if (!name.equals("") && !password.equals("")) {
                new Player(name, password);
                String address = "/userInterface/fxml/Welcome.fxml";
                this.moveToPage(address, username, "welcome");
            } else {
                Alert errorAlert=new Alert(Alert.AlertType.ERROR);
                errorAlert.setContentText("you must fill all fields");
                errorAlert.show();
            }
        }
    }
}
