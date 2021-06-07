package model;

import javafx.scene.Node;
import javafx.scene.layout.GridPane;

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
    private int numberOfHouses;

    {
        bombsLoc = new ArrayList<>();
    }

    public Map(String mapShape, int xSize, int ySize) {
        this.xSize = xSize;
        this.ySize = ySize;
        this.mapShape = mapShape;
        addRandomBomb();
        setNumberOfHouses();
        MapAdaptor.setMapPane(this);
        allMaps.add(this);
    }

    public Map(String randomMap) {
        this.xSize = 27;
        this.ySize = 27;
        this.mapShape = randomMap;
        addRandomBomb();
        MapAdaptor.setMapPane(this);
        setNumberOfHouses();
    }

    public static ArrayList<Map> getAllMaps() {
        return allMaps;
    }

    public static void setAllPlayer(ArrayList<Map> allMaps) {
        Map.allMaps = allMaps;
    }

    public String getMapShape() {
        return mapShape;
    }

    public int getYSize() {
        return ySize;
    }

    public int getXSize() {
        return xSize;
    }

    private void addRandomBomb() {
        Random random = new Random();
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
        return MapAdaptor.getBoardWithMap(this);
    }

    public MapHouse getMapHouse(int row, int column) {
        GridPane mapPane = getMapPane();
        for (Node node : mapPane.getChildren()) {
            MapHouse mapHouse = (MapHouse) node;
            if (mapHouse.getRow() == row && mapHouse.getColumn() == column) {
                return mapHouse;
            }
        }
        return null;
    }

    public ArrayList<Integer> getBombsLoc() {
        return bombsLoc;
    }

    public int getNumberOfHouse() {
        return numberOfHouses;
    }


    public void setNumberOfHouses() {
        int counter = 0;
        for (int i = 0; i < mapShape.length(); i++) {
            if (mapShape.charAt(i) == '0') {
                counter++;
            }
        }
        numberOfHouses = counter;
    }

}
