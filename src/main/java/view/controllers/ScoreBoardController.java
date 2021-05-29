package view.controllers;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Player;

import java.io.IOException;
import java.util.Objects;

public class ScoreBoardController {

    @FXML
    public void initialize(){

    }
    public Label title;
    public Button generateChart;
    public TableView scoreBoard;
    public TableColumn playerName;
    public TableColumn looses;
    public TableColumn draws;
    public TableColumn wins;
    public TableColumn scores;
    public AnchorPane pane;
    Parent parent;


    public void generateTableView() {
        scoreBoard = new TableView<Player>();
        TableColumn<Player, String> playerName = new TableColumn<>("name");
        playerName.setCellValueFactory(new PropertyValueFactory<>("Name"));

        TableColumn<Player, Integer> wins = new TableColumn<>("wins");
        wins.setCellValueFactory(new PropertyValueFactory<>("Wins"));

        TableColumn<Player, Integer> draws = new TableColumn<>("draws");
        draws.setCellValueFactory(new PropertyValueFactory<>("Draws"));

        TableColumn<Player, Integer> looses = new TableColumn<>("looses");
        looses.setCellValueFactory(new PropertyValueFactory<>("Looses"));

        TableColumn<Player, Integer> scores = new TableColumn<>("score");
        scores.setCellValueFactory(new PropertyValueFactory<>("score"));

        scoreBoard.getColumns().add(playerName);
        scoreBoard.getColumns().add(wins);
        scoreBoard.getColumns().add(draws);
        scoreBoard.getColumns().add(looses);
        scoreBoard.getColumns().add(scores);

        scoreBoard.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        for (Player player : Player.getAllPlayer()) {
            scoreBoard.getItems().add(player);
        }

        scoreBoard.setLayoutX(35.0);
        scoreBoard.setLayoutY(124.0);
        scoreBoard.setPrefHeight(100.0);
        scoreBoard.setPrefWidth(379.0);

        //  <TableColumn fx:id="draws" prefWidth="75.0" text="Draws" />
        playerName.setPrefWidth(75.0);
        wins.setPrefWidth(75.0);
        draws.setPrefWidth(75.0);
        looses.setPrefWidth(75.0);
        scores.setPrefWidth(75.0);
        scoreBoard.setStyle("-fx-border-color: black");


        pane.getChildren().add(scoreBoard);
    }

    public void switchToWelcomeScene(ActionEvent event) {
        try {
            parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/userInterface/fxml/Welcome.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.show();
    }
}
