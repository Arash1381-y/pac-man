package model;

public class LoginUser {
    private static Player currentPlayer;

    public static Player getPlayer() {
        return currentPlayer;
    }

    public static void setCurrentPlayer(Player currentPlayer) {
        LoginUser.currentPlayer = currentPlayer;
    }
}
