package model;

import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;

public class MapHouse {
    private static ArrayList<MapHouse> allMapHouses;

    static {
        allMapHouses = new ArrayList<>();
    }

    AnchorPane pane;
    int row;
    int column;
    boolean isHereBefore;

    {
        isHereBefore = false;
    }

    public MapHouse(int row, int column, AnchorPane pane) {
        this.pane = pane;
        this.row = row;
        this.column = column;
        allMapHouses.add(this);
    }

    public static MapHouse getMapHouseByDir(int row, int column) {
        for (MapHouse mapHouse : allMapHouses) {
            if (mapHouse.row == row && mapHouse.column == column) {
                return mapHouse;
            }
        }
        return null;
    }

    public static void clear() {
        allMapHouses.clear();
    }

    public boolean isHereBefore() {
        return isHereBefore;
    }

    public void setHereBefore() {
        isHereBefore = true;
    }

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }

    public AnchorPane getPane() {
        return pane;
    }
}
