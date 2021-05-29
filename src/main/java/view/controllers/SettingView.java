package view.controllers;

import controller.SettingController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Polygon;
import model.GameProp;
import model.Map;

import java.io.IOException;
import java.util.ArrayList;

public class SettingView {
    private static int mapNumber;

    static {
        mapNumber = 0;
    }

    public AnchorPane anchorPane;
    public Label mapSize;
    public Label pointsNum;
    public Polygon next;
    public Polygon previous;
    public Image pacman;
    public Image redPacMan;
    public Label error;
    ArrayList<GridPane> maps;
    private final SettingController control;

    {
        redPacMan = new Image("/pictures/pacmanPic/redPacman.png");
        control = new SettingController();
        maps = new ArrayList<>();
    }

    @FXML
    public void initialize() {
        for (Map map : Map.getAllMaps()) {
            GridPane gridMap = map.getMapPane();
            gridMap.setStyle("-fx-border-color: white; -fx-border-width: 4");
            gridMap.setPrefHeight(410);
            gridMap.setPrefWidth(410);
            gridMap.setLayoutX(431);
            gridMap.setLayoutY(109);
               maps.add(gridMap);
        }
        mapSize.setText(Map.getAllMaps().get(mapNumber).getXSize() + "X" + Map.getAllMaps().get(mapNumber).getYSize());
        pointsNum.setText(String.valueOf(Map.getAllMaps().get(mapNumber).getNumberOfHouse()));
        anchorPane.getChildren().add(maps.get(mapNumber));
    }

    public void goToPrevious() {
        anchorPane.getChildren().remove(maps.get(mapNumber));
        if (mapNumber != 0) {
            mapNumber--;
        } else {
            mapNumber = maps.size() - 1;
        }
        pointsNum.setText(String.valueOf(Map.getAllMaps().get(mapNumber).getNumberOfHouse()));
        anchorPane.getChildren().add(maps.get(mapNumber));
        mapSize.setText(Map.getAllMaps().get(mapNumber).getXSize() + "X" + Map.getAllMaps().get(mapNumber).getYSize());
        GameProp.setPane(maps.get(mapNumber));
    }

    public void goToNext() {
        anchorPane.getChildren().remove(maps.get(mapNumber));
        if (mapNumber != maps.size() - 1) {
            mapNumber++;
        } else {
            mapNumber = 0;
        }
        pointsNum.setText(String.valueOf(Map.getAllMaps().get(mapNumber).getNumberOfHouse()));
        anchorPane.getChildren().add(maps.get(mapNumber));
        mapSize.setText(Map.getAllMaps().get(mapNumber).getXSize() + "X" + Map.getAllMaps().get(mapNumber).getYSize());
        GameProp.setPane(maps.get(mapNumber));
    }

    public void switchToWelcomePage() throws IOException {
        String address = "/userInterface/fxml/Welcome.fxml";
        control.moveToPage(address, next, "welcome");
    }

    public void clickOnPacMan(MouseEvent event) {
        error.setText("");
        ImageView pacMan = (ImageView) event.getTarget();
        if (!pacMan.getImage().equals(redPacMan)) {
            if (GameProp.getPacManLife() <= 2) {
                error.setStyle("-fx-text-fill: red; -fx-font-size: 13");
                error.setText("pacman life must be at least 2!");
            } else {
                control.reduceHealth();
                pacMan.setImage(redPacMan);
            }
        } else {
            control.increaseHealth();
            pacMan.setImage(pacman);
        }
    }

}
