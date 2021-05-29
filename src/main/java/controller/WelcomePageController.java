package controller;

import javafx.scene.media.MediaPlayer;

public class WelcomePageController extends Controller {
    private static MediaPlayer mediaPlayer;

    public static void setMedia(MediaPlayer mediaPlayer) {
        WelcomePageController.mediaPlayer = mediaPlayer;
    }

    public void play() {
        mediaPlayer.play();
    }

    public void stop() {
        mediaPlayer.stop();
    }
}
