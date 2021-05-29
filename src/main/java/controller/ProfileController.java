package controller;

import javafx.animation.TranslateTransition;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import model.LoginUser;
import model.Player;

public class ProfileController extends Controller {
    TranslateTransition move = new TranslateTransition();

    public void changePasswordAnimationMouseEnter(ImageView imageView) {
        move.stop();
        move.setDuration(Duration.seconds(1));
        move.setNode(imageView);
        move.setFromX(imageView.getX());
        move.setToX(420);
        move.play();
        imageView.setX(imageView.getX());
    }

    public void changePasswordAnimationMouseExit(ImageView imageView) {
        move.stop();
        if (imageView.getX() == imageView.getX()) {
            move.setDuration(Duration.seconds(1));
            move.setNode(imageView);
            move.setFromX(420);
            move.setToX(6);
            move.play();
        }
    }

    public void deleteMouseEnter(ImageView imageView) {
        move.stop();
        move.setDuration(Duration.seconds(1));
        move.setNode(imageView);
        move.setFromX(imageView.getX());
        move.setToX(370);
        move.play();
        imageView.setX(imageView.getX());
    }

    public void deleteMouseExit(ImageView imageView) {
        move.stop();
        if (imageView.getX() == imageView.getX()) {
            move.setDuration(Duration.seconds(1));
            move.setNode(imageView);
            move.setFromX(370);
            move.setToX(imageView.getX());
            move.play();
        }
    }

    public void scoresMouseEnter(ImageView imageView) {
        move.stop();
        move.setDuration(Duration.seconds(1));
        move.setNode(imageView);
        move.setFromX(imageView.getX());
        move.setToX(470);
        move.play();
        imageView.setX(imageView.getX());
    }

    public void scoreMouseExit(ImageView imageView) {
        move.stop();
        if (imageView.getX() == imageView.getX()) {
            move.setDuration(Duration.seconds(1));
            move.setNode(imageView);
            move.setFromX(470);
            move.setToX(imageView.getX());
            move.play();
        }
    }

    public void deleteAccount() {
        Player.getAllPlayer().remove(LoginUser.getPlayer());
        LoginUser.setCurrentPlayer(null);
    }

}
