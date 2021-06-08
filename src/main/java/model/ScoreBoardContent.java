package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class ScoreBoardContent {
    private static ArrayList<ScoreBoardContent> allRank;

    static {
        allRank = new ArrayList<>();
    }

    int rank;
    String playerName;
    int score;

    public ScoreBoardContent(String playerName, int score) {
        this.playerName = playerName;
        this.score = score;
    }


    public static void getData(ArrayList<Game> games) {
        ArrayList<Game> rankAbleGames = new ArrayList<>();
        for (Game game : games) {
            if (game.isGameFinished() && Player.getAllPlayer().contains(game.getPlayer())) {
                rankAbleGames.add(game);
            }
        }
        Comparator<Game> compare = Comparator.comparing(Game::getScores, Comparator.reverseOrder());
        Game[] sortedGames = rankAbleGames.toArray(new Game[0]);
        Arrays.sort(sortedGames, compare);
        int limit = Math.min(sortedGames.length, 10);
        for (int i = 0; i < limit; i++) {
            if(isRepeatedBefore(sortedGames[i].getPlayer().getName(), sortedGames[i].getScores())){
                if (sortedGames.length > limit) {
                    limit++;
                }
            }else {
                allRank.add(new ScoreBoardContent(sortedGames[i].getPlayer().getName(), sortedGames[i].getScores()));
            }
        }
        setRank();
    }

    public static ArrayList<ScoreBoardContent> getAllRank() {
        return allRank;
    }

    public static void setAllRank(ArrayList<ScoreBoardContent> allRank) {
        ScoreBoardContent.allRank = allRank;
    }

    public static void setRank() {
        Comparator<ScoreBoardContent> compare = Comparator.comparing(ScoreBoardContent::getScore, Comparator.reverseOrder());
        ScoreBoardContent[] sortedData = allRank.toArray(new ScoreBoardContent[0]);
        Arrays.sort(sortedData, compare);
        int point = -1;
        int rank = 1;
        for (ScoreBoardContent sortedDatum : sortedData) {
            if (point == sortedDatum.score) {
                rank--;
            }
            sortedDatum.rank = rank;
            point = sortedDatum.score;
            rank++;
        }
    }

    public int getScore() {
        return score;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getRank() {
        return rank;
    }

    private static boolean isRepeatedBefore(String name , int score){
        for (ScoreBoardContent scoreBoardContent : allRank) {
            if (scoreBoardContent.score == score && scoreBoardContent.playerName.equals(name)){
                return true;
            }
        }
        return false;
    }


}
