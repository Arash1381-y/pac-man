package controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import model.*;
import view.controllers.GamePlayView;

import java.util.Objects;
import java.util.Random;

enum GameState {
    PAUSED,
    PLAYING,
    NOT_STARTED,
}


public class GamePlayController extends Controller {


    private final Map gameBoard;
    private final Label scorePlace;

    private final Image yellowGhostPic;
    private final Image pinkGhostPic;
    private final Image blueGhostPic;
    private final Image greenGhostPic;

    private final Game game;
    private final GamePlayView gamePlayView;

    MediaPlayer chomp;
    MediaPlayer eatGhost;
    MediaPlayer eatPacman;

    private Pacman pacman;
    private Ghost greenGhost;
    private Ghost yellowGhost;
    private Ghost blueGhost;
    private Ghost pinkGhost;

    private GameState gameState;

    private Timeline pacmanMovementAnimation;
    private Timeline pacmanMouthAnimation;
    private Timeline ghostMovementAnimation;
    private Timeline scaryGhostsAnimation;

    private boolean areGhostsEternal;
    private boolean isSoundEffectDisable;

    {
        chomp = new MediaPlayer(new Media(Objects.requireNonNull(getClass().getResource("/music&soundeffect/eatPoint.wav")).toString()));
        eatGhost = new MediaPlayer(new Media(Objects.requireNonNull(getClass().getResource("/music&soundeffect/eatghost.wav")).toString()));
        eatPacman = new MediaPlayer(new Media(Objects.requireNonNull(getClass().getResource("/music&soundeffect/eatPacman.wav")).toString()));

        gameState = GameState.NOT_STARTED;
        yellowGhostPic = new Image("/pictures/gameplaypic/pacManGamePic/ghost-yellow.png");
        greenGhostPic = new Image("/pictures/gameplaypic/pacManGamePic/ghost-green.png");
        pinkGhostPic = new Image("/pictures/gameplaypic/pacManGamePic/ghost-pink.png");
        blueGhostPic = new Image("/pictures/gameplaypic/pacManGamePic/ghost-blue.png");
        areGhostsEternal = true;
    }

    public GamePlayController(GamePlayView gamePlayView, Game game, Map map, Label scorePlace, boolean isSoundEffectDisable) {
        this.gameBoard = map;
        this.scorePlace = scorePlace;
        this.game = game;
        this.gamePlayView = gamePlayView;
        this.isSoundEffectDisable = isSoundEffectDisable;
        initialize();
    }

    public void initialize() {
        MapHouse pacmanLoc = gameBoard.getMapHouse(16, 16);

        MapHouse greenGhostLoc = gameBoard.getMapHouse(1, 1);
        MapHouse yellowGhostLoc = gameBoard.getMapHouse(1, gameBoard.getYSize() - 2);
        MapHouse pinkGhostLoc = gameBoard.getMapHouse(gameBoard.getXSize() - 2, 1);
        MapHouse blueGhostLoc = gameBoard.getMapHouse(gameBoard.getXSize() - 2, gameBoard.getYSize() - 2);

        pacman = new Pacman(pacmanLoc);

        greenGhost = new Ghost(greenGhostLoc, greenGhostPic);
        yellowGhost = new Ghost(yellowGhostLoc, yellowGhostPic);
        blueGhost = new Ghost(blueGhostLoc, blueGhostPic);
        pinkGhost = new Ghost(pinkGhostLoc, pinkGhostPic);

        assert pacmanLoc != null;
        makeAnimations();
    }

    private void makeAnimations() {
        moveMouthAnimation();
        pacmanMovementAnimation();
        ghostMovementAnimation();
        scaryGhostsAnimation();
    }

    private void pacmanMovementAnimation() {
        pacmanMovementAnimation = new Timeline();
        pacmanMovementAnimation.setCycleCount(Timeline.INDEFINITE);
        pacmanMovementAnimation.getKeyFrames().add(new KeyFrame(
                Duration.seconds(0.3), (ActionEvent event) -> {
            if (isPathInThisLoc(getPacManNextRow(), getPacmanNextColumnIndex())) {
                movePacmanToNextHouse();
            }
        }));
    }

    public void ghostMovementAnimation() {
        ghostMovementAnimation = new Timeline();
        ghostMovementAnimation.setCycleCount(Timeline.INDEFINITE);
        ghostMovementAnimation.getKeyFrames().add(new KeyFrame(
                Duration.seconds(0.5), (ActionEvent event) -> moveGhosts()));
    }

    private void moveMouthAnimation() {
        pacmanMouthAnimation = new Timeline();
        pacmanMouthAnimation.setCycleCount(Timeline.INDEFINITE);
        pacmanMouthAnimation.getKeyFrames().add(new KeyFrame(
                Duration.seconds(0.2), (ActionEvent event) -> pacman.switchPacmanImage()));
        pacmanMouthAnimation.getKeyFrames().add(new KeyFrame(
                Duration.seconds(0.4), (ActionEvent event) -> pacman.switchPacmanImage()));
    }

    public void scaryGhostsAnimation() {
        scaryGhostsAnimation = new Timeline();
        scaryGhostsAnimation.getKeyFrames().add(new KeyFrame(
                Duration.seconds(0), (ActionEvent event) -> changeGhostState())
        );
        scaryGhostsAnimation.getKeyFrames().add(new KeyFrame(
                Duration.seconds(6), (ActionEvent event) -> changeGhostState())
        );
    }

    private void changeGhostState() {
        if (areGhostsEternal) {
            areGhostsEternal = false;
            greenGhost.scareGhost();
            blueGhost.scareGhost();
            pinkGhost.scareGhost();
            yellowGhost.scareGhost();
        } else {
            areGhostsEternal = true;
            greenGhost.backToNormal();
            blueGhost.backToNormal();
            pinkGhost.backToNormal();
            yellowGhost.backToNormal();
        }
    }

    public void movePacman(KeyCode code) {
        if (gameState.equals(GameState.PLAYING)) {
            ghostMovementAnimation.play();
        }
        switch (code) {

            case RIGHT: {
                if (gameState != GameState.PAUSED) {
                    changePacmanDirection(Direction.RIGHT);
                    gameState = GameState.PLAYING;
                }
                break;
            }
            case LEFT: {
                if (gameState != GameState.PAUSED) {
                    changePacmanDirection(Direction.LEFT);
                    gameState = GameState.PLAYING;
                }
                break;
            }
            case DOWN: {
                if (gameState != GameState.PAUSED) {
                    changePacmanDirection(Direction.DOWN);
                    gameState = GameState.PLAYING;
                }
                break;
            }
            case UP: {
                if (gameState != GameState.PAUSED) {
                    changePacmanDirection(Direction.UP);
                    gameState = GameState.PLAYING;
                }
                break;
            }
            case SPACE: {
                if (gameState != GameState.NOT_STARTED) {
                    changeGameState();
                }
                break;
            }
        }
    }

    private void changeGameState() {
        switch (gameState) {
            case PAUSED:
                cont();
                break;
            case PLAYING:
                pause();
                break;
        }
    }

    public void moveGhosts() {
        Random random = new Random();
        findPathForNewHouse(random, greenGhost);
        findPathForNewHouse(random, blueGhost);
        findPathForNewHouse(random, yellowGhost);
        findPathForNewHouse(random, pinkGhost);
    }

    public void changePacmanDirection(Direction dir) {
        if (dir == (Direction.RIGHT)) {
            pacman.setDirection(Direction.RIGHT);
            if (isPathInThisLoc(getPacManNextRow(), getPacmanNextColumnIndex())) {
                pacmanMovementAnimation.play();
                pacmanMouthAnimation.play();
            }

        } else if (dir == (Direction.LEFT)) {
            pacman.setDirection(Direction.LEFT);
            if (isPathInThisLoc(getPacManNextRow(), getPacmanNextColumnIndex())) {
                pacmanMovementAnimation.play();
                pacmanMouthAnimation.play();
            }
        } else if (dir == (Direction.UP)) {
            pacman.setDirection(Direction.UP);
            if (isPathInThisLoc(getPacManNextRow(), getPacmanNextColumnIndex())) {
                pacmanMovementAnimation.play();
                pacmanMouthAnimation.play();
            }
        } else if (dir == Direction.DOWN) {
            pacman.setDirection(Direction.DOWN);
            if (isPathInThisLoc(getPacManNextRow(), getPacmanNextColumnIndex())) {
                pacmanMovementAnimation.play();
                pacmanMouthAnimation.play();
            }
        }
    }

    private void changeGhostDirection(Ghost ghost, Direction newDir) {
        int row = ghost.getGhostHouse().getRow();
        int column = ghost.getGhostHouse().getColumn();
        switch (newDir) {
            case UP:
                row -= 1;
                break;
            case DOWN:
                row += 1;
                break;
            case LEFT:
                column -= 1;
                break;
            case RIGHT:
                column += 1;
                break;
        }

        MapHouse newGhostHouse = gameBoard.getMapHouse(row, column);
        ghost.moveGhostToNewHouse(newGhostHouse);
        assert newGhostHouse != null;
        if (newGhostHouse.getPane().getChildren().contains(pacman.getPacman())) {
            if (areGhostsEternal)
                killPacman();
            else
                eatGhost(ghost);
        }
        ghost.setGhostLastMoveDirection(newDir);
    }

    private void eatGhost(Ghost ghost) {
        if (!isSoundEffectDisable) {
            playSoundEffect(eatGhost);
        }

        ghost.reset(new MapHouse(ghost.getGhostSafeHouseRow(), ghost.getGhostSafeHouseColumn()));
    }

    public void movePacmanToNextHouse() {
        switch (pacman.getDirection()) {
            case RIGHT:
            case DOWN:
            case LEFT:
            case UP:
                MapHouse pacmanLoc = gameBoard.getMapHouse(getPacManNextRow(), getPacmanNextColumnIndex());
                pacman.movePacmanToNewHouse(pacmanLoc);
                assert pacmanLoc != null;
                boolean isGhostEaten = false;
                if (pacmanLoc.getPane().getChildren().contains(greenGhost.getGhost())) {
                    if (areGhostsEternal) {
                        killPacman();
                    } else {
                        eatGhost(greenGhost);
                        isGhostEaten = true;
                    }

                }
                if (pacmanLoc.getPane().getChildren().contains(yellowGhost.getGhost())) {
                    if (areGhostsEternal) {
                        killPacman();
                    } else {
                        eatGhost(yellowGhost);
                        isGhostEaten = true;
                    }
                }
                if (pacmanLoc.getPane().getChildren().contains(pinkGhost.getGhost())) {
                    if (areGhostsEternal) {
                        killPacman();
                    } else {
                        eatGhost(pinkGhost);
                        isGhostEaten = true;
                    }
                }
                if (pacmanLoc.getPane().getChildren().contains(blueGhost.getGhost())) {
                    if (areGhostsEternal) {
                        killPacman();
                    } else {
                        eatGhost(blueGhost);
                        isGhostEaten = true;
                    }
                }
                if (!isGhostEaten) {
                    if (!pacmanLoc.isHereBefore()) {
                        if (pacmanLoc.getHousePoint().isBomb()) {
                            if (areGhostsEternal) {
                                pacmanLoc.eatHousePoint();
                                scaryGhostsAnimation.play();
                            }
                        } else {
                            pacmanLoc.eatHousePoint();
                            if (!isSoundEffectDisable) {
                                playSoundEffect(chomp);
                            }
                            game.updateScore();
                            chomp.play();
                            scorePlace.setText("score : " + game.getScores());
                            if (game.getBoardScore() % ((gameBoard.getNumberOfHouse() - 10) * 5) == 0) {
                                game.setBoardScore(0);
                                gamePlayView.changeMap();
                            }
                        }
                    }
                }

        }
    }

    private void killPacman() {
        pause();
        pacman.reset(gameBoard.getMapHouse(16, 16));
        if (!isSoundEffectDisable) {
            playSoundEffect(eatPacman);
        }
        game.pacmanDie();
        gamePlayView.reducePacmanHealth();
    }

    private void findPathForNewHouse(Random random, Ghost ghost) {
        int ghostDir;
        int row = ghost.getGhostHouse().getRow();
        int column = ghost.getGhostHouse().getColumn();
        Direction direction = ghost.getGhostLastMoveDirection();
        out:
        while (true) {
            ghostDir = random.nextInt(4);
            switch (ghostDir) {
                //UP
                case 0: {
                    if (isPathInThisLoc(row - 1, column) && (
                            direction != Direction.DOWN || isNoOtherWayAvailable(row,
                                    column, Direction.UP))) {
                        changeGhostDirection(ghost, Direction.UP);
                        break out;
                    } else {
                        break;
                    }
                }
                //DOWN
                case 1: {
                    if (isPathInThisLoc(row + 1, column)
                            && (direction != Direction.UP || isNoOtherWayAvailable(row,
                            column, Direction.DOWN))) {
                        changeGhostDirection(ghost, Direction.DOWN);
                        break out;
                    } else {
                        break;
                    }
                }
                //RIGHT
                case 2: {
                    if (isPathInThisLoc(row, column + 1) &&
                            (direction != Direction.LEFT || isNoOtherWayAvailable(row,
                                    column, Direction.RIGHT))) {
                        changeGhostDirection(ghost, Direction.RIGHT);
                        break out;
                    } else {
                        break;
                    }
                }
                //LEFT
                case 3: {
                    if (isPathInThisLoc(row, column - 1) &&
                            (direction != Direction.RIGHT || isNoOtherWayAvailable(row,
                                    column, Direction.LEFT))) {
                        changeGhostDirection(ghost, Direction.LEFT);
                        break out;
                    } else {
                        break;
                    }
                }

            }
        }
    }

    public int getPacmanNextColumnIndex() {
        switch (pacman.getDirection()) {
            case UP:
            case DOWN:
                return pacman.getPacmanHouse().getColumn();
            case LEFT:
                return pacman.getPacmanHouse().getColumn() - 1;
            case RIGHT:
                return pacman.getPacmanHouse().getColumn() + 1;
        }
        return 0;
    }

    public int getPacManNextRow() {
        switch (pacman.getDirection()) {
            case UP:
                return pacman.getPacmanHouse().getRow() - 1;
            case DOWN:
                return pacman.getPacmanHouse().getRow() + 1;
            case LEFT:
            case RIGHT:
                return pacman.getPacmanHouse().getRow();
        }
        return 0;
    }

    public boolean isPathInThisLoc(int row, int column) {
        if (column == gameBoard.getYSize() || row == gameBoard.getXSize() || row < 0 || column < 0) {

            return false;
        } else return gameBoard.getMapShape().charAt(row * gameBoard.getYSize() + column) != ' ';
    }

    private boolean isNoOtherWayAvailable(int row, int column, Direction dir) {
        switch (dir) {
            case UP:
                return !isPathInThisLoc(row, column + 1) && !isPathInThisLoc(row + 1, column) && !isPathInThisLoc(row, column - 1);
            case DOWN:
                return !isPathInThisLoc(row, column + 1) && !isPathInThisLoc(row - 1, column) && !isPathInThisLoc(row, column - 1);
            case RIGHT:
                return !isPathInThisLoc(row + 1, column) && !isPathInThisLoc(row - 1, column) && !isPathInThisLoc(row, column - 1);
            case LEFT:
                return !isPathInThisLoc(row + 1, column) && !isPathInThisLoc(row - 1, column) && !isPathInThisLoc(row, column + 1);
        }
        return true;
    }

    public void pause() {
        pacmanMouthAnimation.stop();
        pacmanMovementAnimation.stop();
        ghostMovementAnimation.stop();
        chomp.stop();
        eatPacman.stop();
        eatGhost.stop();
    }

    public void cont() {
        gameState = GameState.PLAYING;
        pacmanMouthAnimation.play();
        pacmanMovementAnimation.play();
        ghostMovementAnimation.play();
    }


    public void playSoundEffect(MediaPlayer soundEffect) {
        soundEffect.play();
        soundEffect.seek(soundEffect.getStartTime());
    }

    public void finishGame() {
        game.finishGame();
    }

    public void disableSoundEffect() {
        isSoundEffectDisable = true;
    }

    public void ableSoundEffect() {
        isSoundEffectDisable = false;
    }

    public boolean isSoundEffectDisable() {
        return isSoundEffectDisable;
    }

    public void deleteGhost() {
        greenGhost.removeGhost();
        yellowGhost.removeGhost();
        blueGhost.removeGhost();
        pinkGhost.removeGhost();
    }
}
