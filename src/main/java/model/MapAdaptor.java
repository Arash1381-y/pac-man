package model;

import javafx.geometry.Pos;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;

import java.util.HashMap;

public class MapAdaptor {
    private static final HashMap<Map, GridPane> ALL_GAME_BOARD;

    static {
        ALL_GAME_BOARD = new HashMap<>();
    }

    public static void setMapPane(Map map) {
        GridPane mapPane = makeAGrid(map);
        mapPane.setPrefHeight(410);
        mapPane.setPrefWidth(410);
        ALL_GAME_BOARD.put(map, mapPane);
    }

    private static GridPane makeAGrid(Map map) {
        GridPane mapPane = new GridPane();
        mapPane.setAlignment(Pos.CENTER);
        for (int row = 0; row < map.getXSize(); row++) {
            for (int column = 0; column < map.getYSize(); column++) {
                AnchorPane pane = new AnchorPane();
                pane.setPrefWidth(16.18518518518519);
                pane.setPrefHeight(16.38518518518519);
                if (map.getMapShape().charAt(row * map.getYSize() + column) == ' ')
                    pane.setStyle("-fx-background-color: #ff9000; -fx-border-color: #1a1818; -fx-border-width: 0.3");
                else {
                    pane.setStyle("-fx-background-color: #0e0d0d; -fx-border-color: #040443; -fx-border-width: 0.3");
                    Circle circle = new Circle();
                    if (map.getBombsLoc().contains(row * map.getYSize() + column)) {
                        circle.setStyle("-fx-fill: #ffffff");
                        circle.setRadius(1.8);
                    } else {
                        circle.setRadius(2.0);
                        circle.setStyle("-fx-fill: #f1ba12");
                    }
                    circle.setCenterX(7);
                    circle.setCenterY(7);
                    pane.getChildren().add(circle);
                }

                mapPane.add(pane, column, row);
            }
        }
        return mapPane;
    }

    public static GridPane getBoardWithMap(Map map) {
        return ALL_GAME_BOARD.get(map);
    }

}
