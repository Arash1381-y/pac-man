package controller;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.Player;


public class RegisterController extends Controller {

    public void register(String name, Label error, String password, TextField username, TextField userPass) {
        if (username.getText().equals("user name")) {
            error.setText("you must fill username and password !!!!");
        } else {
            Player player = Player.getPlayerByName(name);
            if (player != null) {
                error.setStyle("-fx-font-size: 14");
                error.setText(name + " used before try a new one");
                username.clear();
                userPass.clear();
                return;
            }
            if (!name.equals("") && !password.equals("")) {
                new Player(name, password);
                error.setStyle(" -fx-font-size: 14");
                error.setText("player registered successfully");
                username.clear();
                userPass.clear();
            } else {
                error.setStyle(" -fx-font-size: 14");
                error.setText("you must fill username and password !!!!");
            }
        }
    }
}
