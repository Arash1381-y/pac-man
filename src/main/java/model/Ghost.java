package model;

import animatefx.animation.Flash;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Ghost {
    private Group ghost;
    private Direction ghostLastMoveDirection;
    private MapHouse ghostHouse;
    private final ImageView ghostPic;
    private final Image ghostNormalPic;
    private final int ghostSafeHouseRow;
    private final int ghostSafeHouseColumn;

    public Ghost(MapHouse ghostHouse, Image ghostImage) {
        ghost = new Group();
        ghostNormalPic = ghostImage;
        ghostPic = new ImageView(ghostImage);
        ghostPic.setFitHeight(15);
        ghostPic.setFitWidth(15);
        ghost.getChildren().add(ghostPic);
        this.ghostHouse = ghostHouse;
        this.ghostHouse.getPane().getChildren().add(ghost);
        ghostSafeHouseRow = ghostHouse.getRow();
        ghostSafeHouseColumn = ghostHouse.getColumn();
    }

    public Group getGhost() {
        return ghost;
    }

    public Direction getGhostLastMoveDirection() {
        return ghostLastMoveDirection;
    }

    public void setGhostLastMoveDirection(Direction ghostLastMoveDirection) {
        this.ghostLastMoveDirection = ghostLastMoveDirection;
    }

    public MapHouse getGhostHouse() {
        return ghostHouse;
    }

    public void moveGhostToNewHouse(MapHouse newGhostHouse) {
        ghostHouse.getPane().getChildren().remove(ghost);
        ghostHouse = newGhostHouse;
        ghostHouse.getPane().getChildren().add(ghost);
    }

    public void scareGhost() {
        ghostPic.setImage(new Image("/pictures/gameplaypic/pacManGamePic/ghost-red.png"));
    }

    public void backToNormal() {
        ghostPic.setImage(ghostNormalPic);
    }

    public void reset(MapHouse newPacmanHouse) {
        ghostHouse.getPane().getChildren().remove(ghost);
        ghostHouse = newPacmanHouse;
        new Flash(ghost).play();
        ghostHouse.getPane().getChildren().add(ghost);
    }

    public int getGhostSafeHouseRow() {
        return ghostSafeHouseRow;
    }

    public int getGhostSafeHouseColumn() {
        return ghostSafeHouseColumn;
    }

    public void removeGhost(){
        ghost = null;
    }
}
