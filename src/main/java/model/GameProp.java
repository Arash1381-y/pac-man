package model;

import javafx.scene.layout.GridPane;

public class GameProp {
    private static int pacManLife;
    private static GridPane gameMap;

    static {
        gameMap = Map.getAllMaps().get(0).getMapPane();
        pacManLife = 5;
    }

    private Player player;

    public static void reduceHealth() {
        pacManLife--;
    }

    public static void increaseHealth() {
        pacManLife++;
    }

    public static int getPacManLife() {
        return pacManLife;
    }

    public static GridPane getPane() {
        return gameMap;
    }

    public static void setPane(GridPane pane) {
        gameMap = pane;
    }
}
