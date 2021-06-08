package model;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Pacman {
    private final static Image MOUTH_UP;
    private final static Image MOUTH_DOWN;
    private final static Image MOUTH_RIGHT;
    private final static Image MOUTH_LEFT;
    private final static Image MOUTH_CLOSE;

    static {
        MOUTH_CLOSE = new Image("/pictures/gameplaypic/pacManGamePic/packman-closed.png");
        MOUTH_UP = new Image("/pictures/gameplaypic/pacManGamePic/packman-up-open.png");
        MOUTH_DOWN = new Image("/pictures/gameplaypic/pacManGamePic/packman-down-open.png");
        MOUTH_RIGHT = new Image("/pictures/gameplaypic/pacManGamePic/packman-right-open.png");
        MOUTH_LEFT = new Image("/pictures/gameplaypic/pacManGamePic/packman-left-open.png");
    }

    private final Group pacman;
    private final ImageView pacmanPic;
    private Direction direction;
    private MapHouse pacmanHouse;

    public Pacman(MapHouse pacmanHouse) {
        pacman = new Group();
        pacmanPic = new ImageView(MOUTH_CLOSE);
        pacmanPic.setFitHeight(15);
        pacmanPic.setFitWidth(15);
        pacman.getChildren().add(pacmanPic);
        this.pacmanHouse = pacmanHouse;
        this.pacmanHouse.getPane().getChildren().add(pacman);
    }

    public Group getPacman() {
        return pacman;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public MapHouse getPacmanHouse() {
        return pacmanHouse;
    }

    public void movePacmanToNewHouse(MapHouse newPacmanHouse) {
        pacmanHouse.getPane().getChildren().remove(pacman);
        pacmanHouse = newPacmanHouse;
        pacmanHouse.getPane().getChildren().add(pacman);
    }

    public void switchPacmanImage() {
        if (pacmanPic.getImage() == MOUTH_CLOSE) {
            switch (direction) {
                case UP:
                    pacmanPic.setImage(MOUTH_UP);
                    break;
                case DOWN:
                    pacmanPic.setImage(MOUTH_DOWN);
                    break;
                case LEFT:
                    pacmanPic.setImage(MOUTH_LEFT);
                    break;
                case RIGHT:
                    pacmanPic.setImage(MOUTH_RIGHT);
                    break;
            }
        } else {
            pacmanPic.setImage(MOUTH_CLOSE);
        }
    }

    public void reset(MapHouse newPacmanHouse) {
        pacmanHouse.getPane().getChildren().remove(pacman);
        pacmanHouse = newPacmanHouse;
        pacmanHouse.getPane().getChildren().add(pacman);
    }
}
