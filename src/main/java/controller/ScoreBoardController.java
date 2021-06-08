package controller;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Game;
import model.ScoreBoardContent;

public class ScoreBoardController extends Controller {
    public void fillScoreBoard(TableView<ScoreBoardContent> scoreBoard) {
        ScoreBoardContent.getData(Game.getAllGames());
        TableColumn<ScoreBoardContent, Integer> rank = new TableColumn<>("rank");
        TableColumn<ScoreBoardContent, String> playerName = new TableColumn<>("player name");
        TableColumn<ScoreBoardContent, Integer> scores = new TableColumn<>("score");
        rank.setCellValueFactory(new PropertyValueFactory<>("Rank"));
        playerName.setCellValueFactory(new PropertyValueFactory<>("playerName"));
        scores.setCellValueFactory(new PropertyValueFactory<>("score"));


        scoreBoard.getColumns().addAll(rank, playerName, scores);
        System.out.println(scoreBoard.getColumns().size());
        for (ScoreBoardContent scoreBoardContent : ScoreBoardContent.getAllRank()) {
            scoreBoard.getItems().add(scoreBoardContent);
        }
        scoreBoard.getSortOrder().add(rank);
        scoreBoard.sort();
        rank.setPrefWidth(50);
        playerName.setPrefWidth(154);
        scores.setPrefWidth(190);
        rank.setResizable(false);
        playerName.setResizable(false);
        scores.setResizable(false);
        rank.setStyle("-fx-background-color: linear-gradient(to bottom, transparent, transparent);-fx-text-fill: lightBlue;");
        playerName.setStyle("-fx-background-color: linear-gradient(to bottom, transparent, transparent);-fx-text-fill: lightBlue;");
        scores.setStyle("-fx-background-color: linear-gradient(to bottom, transparent, transparent);-fx-text-fill: lightBlue;");
        rank.setSortable(false);
        playerName.setSortable(false);
        scores.setSortable(false);

    }
}
