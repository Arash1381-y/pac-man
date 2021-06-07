package controller;

import javafx.scene.media.MediaPlayer;
import model.Map;
import model.MapAdaptor;

public class WelcomePageController extends Controller {
    private static MediaPlayer mediaPlayer;
    private static boolean isMusicPlaying;
    private static boolean isSoundMuted;

    static {
        isMusicPlaying = true;
        isSoundMuted = false;
    }

    public static void setMedia(MediaPlayer mediaPlayer) {
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        WelcomePageController.mediaPlayer = mediaPlayer;

    }

    public static boolean isIsMusicPlaying() {
        return isMusicPlaying;
    }

    public static boolean isSoundMuted() {
        return isSoundMuted;
    }

    public  void setIsSoundMuted(boolean value) {
        isSoundMuted = value;
    }

    public void play() {
        mediaPlayer.play();
        isMusicPlaying = true;
    }

    public void stop() {
        mediaPlayer.stop();
        isMusicPlaying = false;
    }

    public void createMaps() {
        for (Map map : Map.getAllMaps()) {
            MapAdaptor.setMapPane(map);
        }
    }
}
