package model;

import java.util.ArrayList;

public class Player {
    static ArrayList<Player> allPlayer;

    static {
        allPlayer = new ArrayList<>();
    }

    String name;
    String password;
    int score;

    {
        score = 0;
    }

    public Player(String name, String password) {
        this.name = name;
        this.password = password;
        allPlayer.add(this);
    }

    public static ArrayList<Player> getAllPlayer() {
        return allPlayer;
    }

    public static void setAllPlayer(ArrayList<Player> allPlayer) {
        Player.allPlayer = allPlayer;
    }

    public static Player getPlayerByName(String name) {
        for (Player player : allPlayer) {
            if (player.name.equals(name)) {
                return player;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public boolean isPasswordCorrect(String password) {
        return password.equals(this.password);
    }

    public int getScore() {
        return score;
    }

    public void setNewPassword(String password) {
        this.password = password;
    }
}
