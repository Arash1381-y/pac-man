package controller;

import animatefx.animation.FadeIn;
import animatefx.animation.FadeOut;
import javafx.animation.SequentialTransition;
import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import model.LoginUser;
import model.Player;

import java.io.IOException;


public class LoginController extends Controller {
    public void loginUser(String name, String password, Label error, TextField username, TextField userPass) throws IOException {
        if (name.equals("") || password.equals("")) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setContentText("please fill all the fields");
            errorAlert.show();
            return;
        }
        for (Player player : Player.getAllPlayer()) {
            if (player.getName().equals(name)) {
                if (player.isPasswordCorrect(password)) {
                    LoginUser.setCurrentPlayer(player);
                    String address = "/userInterface/fxml/Welcome.fxml";
                    moveToPage(address, error, "welcome");
                    return;
                } else {
                    Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                    errorAlert.setContentText("incorrect password");
                    errorAlert.show();
                }
                return;
            }
        }
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setContentText("no user find !");
        errorAlert.show();
        username.clear();
        userPass.clear();
    }

    public void runGhostTransition(ImageView node) {
        TranslateTransition moveOne = new TranslateTransition();
        moveOne.setDuration(Duration.seconds(2));
        moveOne.setNode(node);
        moveOne.setFromX(30);
        moveOne.setToX(300);

        TranslateTransition moveTwo = new TranslateTransition();
        moveTwo.setDuration(Duration.seconds(2));
        moveTwo.setNode(node);
        moveTwo.setFromY(43);
        moveTwo.setToY(300);

        TranslateTransition moveThree = new TranslateTransition();
        moveThree.setDuration(Duration.seconds(2));
        moveThree.setNode(node);
        moveThree.setFromX(300);
        moveThree.setToX(30);

        TranslateTransition moveFour = new TranslateTransition();
        moveFour.setDuration(Duration.seconds(2));
        moveFour.setNode(node);
        moveFour.setFromY(300);
        moveFour.setToY(43);

        SequentialTransition moveCombination = new SequentialTransition(moveOne, moveTwo, moveThree, moveFour);
        moveCombination.setCycleCount(Transition.INDEFINITE);
        moveCombination.play();
    }

    public void changePic(ImageView node) {
        FadeOut fadeOut = new FadeOut(node);
        FadeIn fadeIn = new FadeIn(node);
        fadeOut.playOnFinished(fadeIn);
        fadeIn.play();
        Image image = new Image("pictures/ghostPicInMenus/ghostPic.png");
        node.setImage(image);
    }
}
