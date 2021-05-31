package controller;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import view.controllers.*;

import java.io.IOException;

public abstract class Controller {
    public void moveToPage(String address, Node node, String type) throws IOException {
        Parent parent;
        Stage stage;
        Scene scene;
        FXMLLoader loader = new FXMLLoader(getClass().getResource(address));
        Scene current = node.getScene();
        parent = loader.load();
        parent.translateYProperty().set(current.getHeight());
        Timeline timeline = new Timeline();
        KeyValue kv = new KeyValue(parent.translateYProperty(), 0, Interpolator.EASE_BOTH);
        KeyFrame kf = new KeyFrame(Duration.seconds(1), kv);

        timeline.getKeyFrames().add(kf);
        stage = (Stage) node.getScene().getWindow();
        scene = new Scene(parent, 863.0, 555.0);
        stage.setScene(scene);
        scene.getRoot().requestFocus();
        loadPane(loader, type, scene);
        timeline.play();
    }

    private void loadPane(FXMLLoader loader, String page, Scene scene) {

        switch (page) {
            case "welcome": {
                WelcomeView controller = loader.getController();
                controller.anchorPane.resize(863.0, 555.0);
                break;
            }
            case "login": {
                LoginUserView controller = loader.getController();
                controller.anchorPane.resize(863.0, 555.0);
                break;
            }
            case "register": {
                RegisterView controller = loader.getController();
                controller.anchorPane.resize(863.0, 555.0);
                break;
            }
            case "profile": {
                ProfileView controller = loader.getController();
                controller.anchorPane.resize(863.0, 555.0);
                break;
            }
            case "changePass": {
                ChangePassView controller = loader.getController();
                controller.anchorPane.resize(863.0, 555.0);
                break;
            }
            case "setting": {
                SettingView controller = loader.getController();
                controller.anchorPane.resize(863.0, 555.0);
                break;
            }
            case "playGame": {
                GamePlayView controller = loader.getController();
                controller.anchorPane.resize(863.0, 555.0);
                scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
                    @Override
                    public void handle(KeyEvent event) {
                        controller.pressKey(event.getCode());
                    }
                });
                break;
            }
        }
    }

}
