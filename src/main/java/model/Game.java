package model;

import java.util.ArrayList;

public class Game {
    private static int pacManLife;
    private static Map gameMap;
    private static final ArrayList<Game> allGames;

    static {
        allGames = new ArrayList<>();
        gameMap = Map.getAllMaps().get(0);
        pacManLife = 5;
    }
    private boolean isGameFinished;
    private int remainLife;
    private int scores;
    private Player player;
    {
        isGameFinished = false;
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

    public int getRemainLife() {
        return remainLife;
    }

    public void pacmanDie() {
        this.remainLife--;
    }

    public void updateScore() {
        scores += 5;
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

    public static ArrayList<Game> getAllGames() {
        return allGames;
    }
}
