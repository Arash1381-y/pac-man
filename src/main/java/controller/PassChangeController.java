package controller;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Player;
import view.controllers.ProfileView;

import java.io.IOException;

public class PassChangeController extends Controller {
    Parent parent;
    Stage stage;
    Scene scene;

    public void apply(Label error, String oldPass, String newPass, Player player) throws IOException {
        if (player.isPasswordCorrect(oldPass)) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/userInterface/fxml/Profile.fxml"));
            parent = loader.load();
            player.setNewPassword(newPass);
            Scene firstScene = error.getScene();
            parent.translateYProperty().set(firstScene.getHeight());
            Timeline timeline = new Timeline();
            KeyValue kv = new KeyValue(parent.translateYProperty(), 0, Interpolator.EASE_IN);
            KeyFrame kf = new KeyFrame(Duration.seconds(1), kv);
            timeline.getKeyFrames().add(kf);
            stage = (Stage) error.getScene().getWindow();
            scene = new Scene(parent, 863.0, 555.0);
            stage.setScene(scene);
            ProfileView controller = loader.getController();
            controller.anchorPane.resize(863.0, 555.0);
            timeline.play();
        }else{
            error.setStyle("-fx-text-fill: #5089B9FF");
            error.setText("password is wrong");
        }
    }
}
