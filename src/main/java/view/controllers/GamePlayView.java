package view.controllers;

import controller.GamePlayController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import model.Game;
import model.LoginUser;

import java.io.IOException;

public class GamePlayView {
    public GamePlayController controller;
    private static int row;
    private static int column;
    public Image redPacMan;
    public Image yellowPacman;
    public Image pacmanUp;
    public Image pacmanDown;
    public Image pacmanLeft;
    public Image pacmanRight;
    public Image pacmanClose;
    public AnchorPane anchorPane;
    public ImageView life1;
    public ImageView life2;
    public ImageView life3;
    public ImageView life4;
    public ImageView life5;
    public GridPane gameBoard;
    public Button back;
    public Label scoreNum;

    {
        row = 0;
        column = 0;
        Game game = new Game(LoginUser.getPlayer());
        redPacMan = new Image("/pictures/pacmanPic/redPacman.png");
        yellowPacman = new Image("/pictures/pacmanPic/yellowPacman.jpg");
    }

    @FXML
    public void initialize() {
        controller = new GamePlayController(Game.getMap(),scoreNum);
        ImageView[] pacmanLives = {life1, life2, life3, life4, life5};
        int pacmanLifePoint = Game.getPacManLife();
        for (int i = 0; i < pacmanLives.length; i++) {
            if (i + 1 <= pacmanLifePoint) {
                pacmanLives[i].setImage(yellowPacman);
            }
        }
        gameBoard = Game.getMap().getMapPane();
        gameBoard.setStyle("-fx-border-color: red; -fx-border-width: 3");
        gameBoard.setLayoutX(200);
        gameBoard.setLayoutY(50);
        anchorPane.getChildren().add(gameBoard);
    }


    public void switchToWelcomePage() throws IOException {
        String address = "/userInterface/fxml/Welcome.fxml";
        controller.moveToPage(address, back, "welcome");
    }

    public void pressKey(KeyCode code) {
        controller.movePacman(code);
    }


}
