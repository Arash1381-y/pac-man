package model;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;

import java.util.ArrayList;
import java.util.Random;

public class Map {
    private static ArrayList<Map> allMaps;

    static {
        allMaps = new ArrayList<>();
    }

    private final ArrayList<Integer> bombsLoc;
    private final String mapShape;
    private final int xSize;
    private final int ySize;
    Random random = new Random();
    private int numberOfHouse;

    {
        numberOfHouse = 0;
        bombsLoc = new ArrayList<>();
    }

    public Map(String mapShape, int xSize, int ySize) {
        this.xSize = xSize;
        this.ySize = ySize;
        this.mapShape = mapShape;
        addRandomBomb();
        allMaps.add(this);
    }

    public Map(String randomMap){
        this.xSize = 27;
        this.ySize = 27;
        this.mapShape = randomMap;
        addRandomBomb();
    }

    public static ArrayList<Map> getAllMaps() {
        return allMaps;
    }

    public static void setAllPlayer(ArrayList<Map> allMaps) {
        Map.allMaps = allMaps;
    }

    public int getYSize() {
        return ySize;
    }

    public int getXSize() {
        return xSize;
    }

    private void addRandomBomb() {
        int numberOfBomb = 10;
        for (int number = 0; number < numberOfBomb; number++) {
            int bomb = random.nextInt(xSize * ySize);
            if (bombsLoc.contains(bomb) || mapShape.charAt(bomb) == ' ') {
                number--;
            }
            bombsLoc.add(bomb);
        }
    }

    public GridPane getMapPane() {
        int counter = 0;
        GridPane mapPane = new GridPane();
        for (int row = 0; row < xSize; row++) {
            for (int column = 0; column < ySize; column++) {
                AnchorPane pane = new AnchorPane();
                pane.setPrefWidth(16.18518518518519);
                pane.setPrefHeight(16.38518518518519);
                if (mapShape.charAt(column * ySize + row) == ' ')
                    pane.setStyle("-fx-background-color: #0024ff; -fx-border-color: #1a1818");
                else {
                    counter++;
                    pane.setStyle("-fx-background-color: #faf7f7; -fx-border-color: #040443");
                    Circle circle = new Circle();
                    if (bombsLoc.contains(row * xSize + column)) {
                        circle.setStyle("-fx-fill: #60ea09");
                        circle.setRadius(2.6);
                    } else {
                        circle.setRadius(2.3);
                        circle.setStyle("-fx-fill: #07b8f8");
                    }
                    circle.setCenterX(7);
                    circle.setCenterY(7);
                    pane.getChildren().add(circle);
                }
                pane.setVisible(true);
                mapPane.add(pane, row, column);
            }
        }
        numberOfHouse = counter;
        return mapPane;
    }


    public int getNumberOfHouse() {
        return numberOfHouse;
    }
}
