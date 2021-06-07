package model;

import javafx.scene.layout.AnchorPane;

public class MapHouse extends AnchorPane {

    int row;
    int column;
    boolean isHereBefore;
    boolean isWall;
    private MapPoint housePoint;

    {
        isHereBefore = false;
        isWall = true;
    }

    public MapHouse(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public boolean isHereBefore() {
        return isHereBefore;
    }

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }

    public AnchorPane getPane() {
        return this;
    }

    public void eatHousePoint() {
        isHereBefore = true;
        this.getChildren().remove(housePoint);
    }

    public void setHousePoint(MapPoint housePoint) {
        isWall = false;
        this.getChildren().add(housePoint);
        this.housePoint = housePoint;
    }

    public boolean isWall() {
        return isWall;
    }

    public void resetHouse() {
        isHereBefore = false;
        this.getChildren().clear();
        if (!isWall) {
            this.getChildren().add(housePoint);
        }
    }

    public MapPoint getHousePoint() {
        return housePoint;
    }
}
