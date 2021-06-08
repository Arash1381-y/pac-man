package view.controllers;

import controller.MapGenerator;
import controller.SettingController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Polygon;
import model.Game;
import model.Map;
import model.MapHouse;

import java.io.IOException;
import java.util.ArrayList;

public class SettingView {
    private static int mapNumber;

    static {
        mapNumber = 0;
    }

    private final SettingController control;
    public AnchorPane anchorPane;
    public Label mapSize;
    public Label pointsNum;
    public Polygon next;
    public Polygon previous;
    public Image yellowPacman;
    public Image redPacMan;
    public Label error;
    public ImageView firstLife;
    public ImageView secondLife;
    public ImageView thirdLife;
    public ImageView forthLife;
    public ImageView fifthLife;
    ArrayList<Map> maps;

    {
        redPacMan = new Image("/pictures/gameplaypic/redPacman.png");
        yellowPacman = new Image("/pictures/gameplaypic/yellowPacman.jpg");
        control = new SettingController();
        maps = new ArrayList<>();
    }

    @FXML
    public void initialize() {
        ImageView[] pacmanLives = {firstLife, secondLife, thirdLife, forthLife, fifthLife};
        int pacmanLifePoint = Game.getPacManLife();
        for (int i = 0; i < pacmanLives.length; i++) {
            if (i + 1 <= pacmanLifePoint) {
                pacmanLives[i].setImage(yellowPacman);
            } else {
                for (int j = i; j <= 4; j++) {
                    pacmanLives[j].setImage(redPacMan);
                }
                break;
            }
        }
        maps.addAll(Map.getAllMaps());
        mapSize.setText(Map.getAllMaps().get(mapNumber).getXSize() + "X" + Map.getAllMaps().get(mapNumber).getYSize());
        pointsNum.setText(String.valueOf(Map.getAllMaps().get(mapNumber).getNumberOfHouse()));

        addMap();
    }

    private void addMap() {
        GridPane gridMap = maps.get(mapNumber).getMapPane();
        setMapSize(gridMap);
        gridMap.setStyle("-fx-border-color: white; -fx-border-width: 4");
        gridMap.setLayoutX(365);
        gridMap.setLayoutY(90);
        anchorPane.getChildren().add(gridMap);

    }

    public void goToPrevious() {
        anchorPane.getChildren().remove(maps.get(mapNumber).getMapPane());
        if (mapNumber != 0) {
            mapNumber--;
        } else {
            mapNumber = maps.size() - 1;
        }
        pointsNum.setText(String.valueOf(Map.getAllMaps().get(mapNumber).getNumberOfHouse()));
        addMap();
        mapSize.setText(Map.getAllMaps().get(mapNumber).getXSize() + "X" + Map.getAllMaps().get(mapNumber).getYSize());
        Game.setMap(maps.get(mapNumber));
    }

    public void goToNext() {
        anchorPane.getChildren().remove(maps.get(mapNumber).getMapPane());
        if (mapNumber != maps.size() - 1) {
            mapNumber++;
        } else {
            mapNumber = 0;
        }
        pointsNum.setText(String.valueOf(Map.getAllMaps().get(mapNumber).getNumberOfHouse()));
        addMap();
        mapSize.setText(Map.getAllMaps().get(mapNumber).getXSize() + "X" + Map.getAllMaps().get(mapNumber).getYSize());
        Game.setMap(maps.get(mapNumber));
    }

    public void switchToWelcomePage() throws IOException {
        String address = "/userInterface/fxml/Welcome.fxml";
        control.moveToPage(address, next, "welcome");
    }

    public void clickOnPacMan(MouseEvent event) {
        error.setText("");
        ImageView pacMan = (ImageView) event.getTarget();
        if (!pacMan.getImage().equals(redPacMan)) {
            if (Game.getPacManLife() <= 2) {
                error.setStyle("-fx-text-fill: red; -fx-font-size: 13");
                error.setText("yellowPacman life must be at least 2!");
            } else {
                control.reduceHealth();
                pacMan.setImage(redPacMan);
            }
        } else {
            control.increaseHealth();
            pacMan.setImage(yellowPacman);
        }
    }

    public void generateMap() {
        anchorPane.getChildren().remove(maps.get(mapNumber).getMapPane());
        String map = MapGenerator.getMaze();
        Map newMap = new Map(map);
        GridPane newMapPane = newMap.getMapPane();
        setMapSize(newMapPane);
        newMapPane.setStyle("-fx-border-color: white; -fx-border-width: 4");
        newMapPane.setPrefHeight(410);
        newMapPane.setPrefWidth(410);
        newMapPane.setLayoutX(365);
        newMapPane.setLayoutY(90);
        pointsNum.setText(String.valueOf(newMap.getNumberOfHouse()));
        anchorPane.getChildren().add(newMapPane);
        mapSize.setText(newMap.getXSize() + "X" + newMap.getYSize());
        Game.setMap(newMap);
    }

    private void setMapSize(GridPane newMapPane) {
        newMapPane.setPrefHeight(410);
        newMapPane.setPrefWidth(410);
        newMapPane.getChildren().forEach(pane -> {
            ((AnchorPane) pane).setPrefWidth(16.38518518518519);
            ((AnchorPane) pane).setPrefHeight(16.38518518518519);
            ((MapHouse) pane).resetHouse();
        });
    }
}
