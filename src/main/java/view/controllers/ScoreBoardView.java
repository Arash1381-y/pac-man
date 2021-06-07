package view.controllers;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Game;
import model.ScoreBoardContent;

import java.io.IOException;
import java.util.Objects;

public class ScoreBoardView {

    public Label title;
    public AnchorPane pane;
    TableView scoreBoard = new TableView<ScoreBoardContent>();
    Parent parent;

    @FXML
    public void initialize() {
        ScoreBoardContent.getData(Game.getAllGames());

        TableColumn<ScoreBoardContent , Integer> rank = new TableColumn<>("rank");
        rank.setCellValueFactory(new PropertyValueFactory<>("Rank"));

        TableColumn<ScoreBoardContent, String> playerName = new TableColumn<>("player mame");
        playerName.setCellValueFactory(new PropertyValueFactory<>("playerName"));

        TableColumn<ScoreBoardContent, Integer> scores = new TableColumn<>("score");
        scores.setCellValueFactory(new PropertyValueFactory<>("score"));

        scoreBoard.getColumns().add(rank);
        scoreBoard.getColumns().add(playerName);
        scoreBoard.getColumns().add(scores);

        scoreBoard.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);


        scoreBoard.setLayoutX(35.0);
        scoreBoard.setLayoutY(124.0);
        scoreBoard.setPrefHeight(210);
        scoreBoard.setPrefWidth(300);
        for (ScoreBoardContent scoreBoardContent : ScoreBoardContent.getAllRank()) {
            scoreBoard.getItems().add(scoreBoardContent);
        }
        rank.setPrefWidth(10);
        playerName.setPrefWidth(150);
        scores.setPrefWidth(50);

        scoreBoard.setStyle("-fx-border-color: black");


        pane.getChildren().add(scoreBoard);
    }


    public void switchToWelcomeScene(ActionEvent event) {
        try {
            parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/userInterface/fxml/Welcome.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        pane.getChildren().remove(scoreBoard);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.show();
    }
}
