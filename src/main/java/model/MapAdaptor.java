package model;

import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;

import java.util.HashMap;

public class MapAdaptor {
    private static final HashMap<Map, GridPane> ALL_GAME_BOARD;

    static {
        ALL_GAME_BOARD = new HashMap<>();
    }

    public static void setMapPane(Map map) {
        GridPane mapPane = makeAGrid(map);
        ALL_GAME_BOARD.put(map, mapPane);
    }

    private static GridPane makeAGrid(Map map) {
        GridPane mapPane = new GridPane();
        mapPane.setAlignment(Pos.CENTER);
        for (int row = 0; row < map.getXSize(); row++) {
            for (int column = 0; column < map.getYSize(); column++) {
                MapHouse pane = new MapHouse(row, column);
                if (map.getMapShape().charAt(row * map.getYSize() + column) == ' ')
                    pane.setStyle("-fx-background-color: #ff9000; -fx-border-color: #1a1818; -fx-border-width: 0.3");
                else {
                    pane.setStyle("-fx-background-color: #0e0d0d; -fx-border-color: #040443; -fx-border-width: 0.3");
                    MapPoint mapPoint;
                    if (map.getBombsLoc().contains(row * map.getYSize() + column)) {
                        mapPoint = new MapPoint(true);
                    } else {
                        mapPoint = new MapPoint(false);
                    }
                    mapPoint.setCenterX(7);
                    mapPoint.setCenterY(7);
                    pane.setHousePoint(mapPoint);
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
