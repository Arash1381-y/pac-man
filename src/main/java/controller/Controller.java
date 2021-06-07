package controller;

import animatefx.animation.SlideInDown;
import animatefx.animation.SlideInRight;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.controllers.*;

import java.io.IOException;

public abstract class Controller {
    Parent parent;

    public void moveToPage(String address, Node node, String type) throws IOException {
        Stage stage;
        Scene scene;
        FXMLLoader loader = new FXMLLoader(getClass().getResource(address));
        Scene current = node.getScene();
        parent = loader.load();
        stage = (Stage) node.getScene().getWindow();
        if (!type.equals("playGame")) {
            scene = new Scene(parent, 863.0, 555.0);
        } else {
            scene = new Scene(parent, 970, 650);
        }
        stage.setScene(scene);
        scene.getRoot().requestFocus();
        loadPane(loader, type, scene);
        stage.show();
    }

    private void loadPane(FXMLLoader loader, String page, Scene scene) {

        switch (page) {
            case "welcome": {
                new SlideInDown(parent).play();
                WelcomeView controller = loader.getController();
                controller.playMusic();
                controller.anchorPane.resize(863.0, 555.0);
                break;
            }
            case "login": {
                new SlideInRight(parent).play();
                LoginUserView controller = loader.getController();
                controller.anchorPane.resize(863.0, 555.0);
                break;
            }
            case "register": {
                new SlideInRight(parent).play();
                RegisterView controller = loader.getController();
                controller.anchorPane.resize(863.0, 555.0);
                break;
            }
            case "profile": {
                new SlideInRight(parent).play();
                ProfileView controller = loader.getController();
                controller.anchorPane.resize(863.0, 555.0);
                break;
            }
            case "changePass": {
                new SlideInRight(parent).play();
                ChangePassView controller = loader.getController();
                controller.anchorPane.resize(863.0, 555.0);
                break;
            }
            case "setting": {
                new SlideInRight(parent).play();
                SettingView controller = loader.getController();
                controller.anchorPane.resize(863.0, 555.0);
                break;
            }
            case "playGame": {
                new SlideInRight(parent).play();
                GamePlayView controller = loader.getController();
                controller.anchorPane.resize(1600, 700);
                scene.setOnKeyPressed(event -> controller.pressKey(event.getCode()));
                break;
            }
        }
    }

}
