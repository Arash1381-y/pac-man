package model;

import javafx.scene.shape.Circle;

public class MapPoint extends Circle {
    private boolean isBomb;

    public MapPoint(boolean isBomb) {
        this.isBomb = isBomb;
        if (isBomb) {
            this.setStyle("-fx-fill: #ffffff");
            this.setRadius(2.8);
        } else {
            this.setStyle("-fx-fill: #f1ba12");
            this.setRadius(2.0);
        }
        this.setLayoutX(0.32);
        this.setLayoutY(0.32);
    }

    public boolean isBomb() {
        return isBomb;
    }
}
