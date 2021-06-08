package view.controllers;


import controller.ScoreBoardController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import model.ScoreBoardContent;

import java.io.IOException;

public class ScoreBoardView {

    public Label title;
    public AnchorPane anchorPane;
    @FXML
    TableView<ScoreBoardContent> scoreBoard;
    private ScoreBoardController controller;

    {
        controller = new ScoreBoardController();
    }

    @FXML
    public void initialize() {
        controller.fillScoreBoard(scoreBoard);
    }


    public void switchToWelcomeScene() throws IOException {
        String address = "/userInterface/fxml/Welcome.fxml";
        controller.moveToPage(address, scoreBoard, "welcome");
    }
}
