package controller;

import javafx.scene.media.MediaPlayer;
import model.Map;
import model.MapAdaptor;

public class WelcomePageController extends Controller {
    private static MediaPlayer mediaPlayer;
    private static boolean isMusicPlaying;

    static {
        isMusicPlaying = true;
    }

    public static void setMedia(MediaPlayer mediaPlayer) {
        WelcomePageController.mediaPlayer = mediaPlayer;
    }

    public static boolean isIsMusicPlaying() {
        return isMusicPlaying;
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
