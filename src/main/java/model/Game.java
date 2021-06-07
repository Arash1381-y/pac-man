package model;

import java.util.ArrayList;

public class Game {
    private static final ArrayList<Game> allGames;
    private static int pacManLife;
    private static Map gameMap;

    static {
        allGames = new ArrayList<>();
        gameMap = Map.getAllMaps().get(0);
        pacManLife = 5;
    }

    private int boardScore;
    private boolean isGameFinished;
    private int remainLife;
    private int scores;
    private final Player player;

    {
        isGameFinished = false;
        boardScore = 0;
        scores = 0;
    }

    public Game(Player player) {
        this.player = player;
        this.remainLife = pacManLife;
        allGames.add(this);
    }

    public static void reduceHealth() {
        pacManLife--;
    }

    public static void increaseHealth() {
        pacManLife++;
    }

    public static Map getMap() {
        return Game.gameMap;
    }

    public static void setMap(Map gameMap) {
        Game.gameMap = gameMap;
    }

    public static int getPacManLife() {
        return pacManLife;
    }

    public static ArrayList<Game> getAllGames() {
        return allGames;
    }

    public int getRemainLife() {
        return remainLife;
    }

    public void pacmanDie() {
        this.remainLife--;
    }

    public void updateScore() {
        scores += 5;
        boardScore += 5;
    }

    public int getScores() {
        return scores;
    }

    public void finishGame() {
        isGameFinished = true;
    }

    public boolean isGameFinished() {
        return isGameFinished;
    }

    public Player getPlayer() {
        return player;
    }

    public int getBoardScore() {
        return boardScore;
    }

    public void setBoardScore(int boardScore) {
        this.boardScore = boardScore;
    }
}
