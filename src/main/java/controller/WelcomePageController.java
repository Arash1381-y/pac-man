package controller;

import javafx.scene.media.MediaPlayer;

public class WelcomePageController extends Controller {
    private static MediaPlayer mediaPlayer;
    private static boolean isMusicPlaying;

    static {
        isMusicPlaying = true;
    }

    public static void setMedia(MediaPlayer mediaPlayer) {
        WelcomePageController.mediaPlayer = mediaPlayer;
    }

    public void play() {
        mediaPlayer.play();
        isMusicPlaying = true;
    }

    public void stop() {
        mediaPlayer.stop();
        isMusicPlaying = false;
    }

    public static boolean isIsMusicPlaying() {
        return isMusicPlaying;
    }
}
