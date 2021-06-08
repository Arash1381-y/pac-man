package view.controllers;

import animatefx.animation.Hinge;
import controller.GamePlayController;
import controller.MapGenerator;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;
import model.*;

import java.io.IOException;
import java.util.Optional;

public class GamePlayView {
    private final Image yellowPacman;
    public AnchorPane anchorPane;
    public ImageView life1;
    public ImageView life2;
    public ImageView life3;
    public ImageView life4;
    public ImageView life5;
    public ImageView sound;
    public GridPane gameBoard;
    public Button exit;
    public Label scoreNum;
    public Image unmute;
    public Label playerInGameName;
    private GamePlayController controller;
    private Game game;

    {
        yellowPacman = new Image("/pictures/gameplaypic/yellowPacman.jpg");
    }

    @FXML
    public void initialize() {
        Player player;
        if ((player = LoginUser.getPlayer()) == null) {
            playerInGameName.setText("Player : Guest");
        } else {
            playerInGameName.setText("Player : " + player.getName());
        }
        game = new Game(LoginUser.getPlayer());
        showPacmanLivesNumber();
        gameBoard = Game.getMap().getMapPane();
        refillTheMap();
        controller = new GamePlayController(this, game, Game.getMap(), scoreNum, false);
        styleGameBoard();
        fadeInNewMap();
        anchorPane.getChildren().add(gameBoard);
    }

    private void showPacmanLivesNumber() {
        ImageView[] pacmanLives = {life1, life2, life3, life4, life5};
        int pacmanLifePoint = game.getRemainLife();
        for (int i = 0; i < pacmanLives.length; i++) {
            if (i + 1 <= pacmanLifePoint) {
                pacmanLives[i].setImage(yellowPacman);
            } else {
                pacmanLives[i].setImage(null);
            }
        }
    }


    public void switchToWelcomePage(MouseEvent event) throws IOException {
        controller.deleteGhost();
        controller.pause();
        showAlertBox(event);
    }

    public void pressKey(KeyCode code) {
        controller.movePacman(code);
    }

    public void reducePacmanHealth() {
        ImageView[] pacmanLives = {life1, life2, life3, life4, life5};
        int pacmanLifePoint = game.getRemainLife();

        for (int i = 0; i < pacmanLives.length; i++) {
            if (i + 1 <= pacmanLifePoint) {
                pacmanLives[i].setImage(yellowPacman);
            } else {
                new Hinge(pacmanLives[i]).play();
                break;
            }
        }
        if (game.getRemainLife() == 0) {
            controller.pause();
            controller.finishGame();
            anchorPane.getChildren().remove(gameBoard);
            Label gameOverLabel = new Label();
            gameOverLabel.setText("GAME OVER");
            gameOverLabel.setStyle("-fx-background-color: black; -fx-border-style: solid; " +
                    "-fx-border-color: red; -fx-border-width: 4; -fx-alignment: center; -fx-text-fill: red; -fx-font-size: 20");
            gameOverLabel.setPrefWidth(700);
            gameOverLabel.setLayoutX(140);
            gameOverLabel.setLayoutY(300);
            anchorPane.getChildren().add(gameOverLabel);
        }
    }

    public void changeMap() {
        Timeline changeMapAnimation = new Timeline();
        changeMapAnimation.setCycleCount(1);
        changeMapAnimation.getKeyFrames().add(new KeyFrame(
                Duration.seconds(0), (ActionEvent event) -> fadeOutOldMap()));
        changeMapAnimation.getKeyFrames().add(new KeyFrame(
                Duration.seconds(6), (ActionEvent event) -> setNewMap()));
        changeMapAnimation.play();
    }

    private void setNewMap() {
        controller.pause();
        controller.deleteGhost();
        String map = MapGenerator.getMaze();
        Map newMap = new Map(map);
        gameBoard = newMap.getMapPane();
        refillTheMap();
        controller = new GamePlayController(this, game, newMap, scoreNum, controller.isSoundEffectDisable());
        gameBoard.setVisible(true);
        anchorPane.getChildren().add(gameBoard);
        fadeInNewMap();
    }

    private void refillTheMap() {
        gameBoard.getChildren().forEach(pane -> {
                    ((MapHouse) pane).setPrefWidth(30);
                    ((MapHouse) pane).setPrefHeight(30);
                    ((MapHouse) pane).resetHouse();
                }
        );
    }

    private void fadeOutOldMap() {
        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setCycleCount(1);
        fadeTransition.setDuration(Duration.seconds(5));
        fadeTransition.setFromValue(10);
        fadeTransition.setToValue(0);
        fadeTransition.setNode(gameBoard);
        fadeTransition.play();

    }

    private void fadeInNewMap() {
        gameBoard.setVisible(false);
        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setCycleCount(1);
        fadeTransition.setDuration(Duration.seconds(5));
        fadeTransition.setFromValue(0);
        gameBoard.setVisible(true);
        fadeTransition.setToValue(10);
        fadeTransition.setNode(gameBoard);
        fadeTransition.play();
        styleGameBoard();
    }

    private void styleGameBoard() {
        gameBoard.setPrefWidth(530);
        gameBoard.setPrefHeight(530);
        gameBoard.setStyle("-fx-border-color: red; -fx-border-width: 3");
        gameBoard.setLayoutX(200);
        gameBoard.setLayoutY(80);
    }

    public void showAlertBox(MouseEvent event) throws IOException {
        if (game.getRemainLife() == 0) {
            controller.finishGame();
            String address = "/userInterface/fxml/Welcome.fxml";
            controller.moveToPage(address, exit, "welcome");
            return;
        }
        ButtonType sure = new ButtonType("im sure", ButtonBar.ButtonData.OK_DONE);
        ButtonType refuse = new ButtonType("cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION, "", sure, refuse);
        confirmation.setTitle("EXIT GAME");
        confirmation.setContentText("YOUR GAME IS UNFINISHED ARE U SURE U WANT TO EXIT ?");
        confirmation.initOwner(((Node) event.getSource()).getScene().getWindow());
        Optional<ButtonType> result = confirmation.showAndWait();
        if (result.get() == sure) {
            controller.finishGame();
            String address = "/userInterface/fxml/Welcome.fxml";
            controller.moveToPage(address, exit, "welcome");
        } else {
            Scene scene = exit.getScene();
            scene.getRoot().requestFocus();
            scene.setOnKeyPressed(event1 -> this.pressKey(event1.getCode()));
            controller.cont();
        }
    }

    public void clickOnSound() {
        if (sound.getImage().equals(unmute)) {
            controller.disableSoundEffect();
            Image image = new Image("/pictures/musicIcon/sound off.png");
            sound.setImage(image);
        } else {
            controller.ableSoundEffect();
            sound.setImage(unmute);
        }
    }
}
