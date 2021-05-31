package model;

public class Game {
    private static int pacManLife;
    private static Map gameMap;

    static {
        gameMap = Map.getAllMaps().get(0);
        pacManLife = 5;
    }

    private int remainLife;
    private int scores;
    private Player player;

    public Game(Player player) {
        this.player = player;
        this.remainLife = Game.getPacManLife();
    }


    public static void reduceHealth() {
        pacManLife--;
    }

    public static void increaseHealth() {
        pacManLife++;
    }

    public static int getPacManLife() {
        return pacManLife;
    }

    public static Map getMap() {
        return Game.gameMap;
    }

    public static void setMap(Map gameMap) {
        Game.gameMap = gameMap;
    }

    public void pacmanDie() {
        this.remainLife--;
    }
}
