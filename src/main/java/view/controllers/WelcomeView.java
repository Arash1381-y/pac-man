package view.controllers;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import controller.WelcomePageController;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import model.Map;
import model.Player;
import model.ScoreBoardContent;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class WelcomeView extends Application {

    public Label Title;
    public Button login;
    public Button register;
    public Button scoreBoard;
    public Button profile;
    public AnchorPane anchorPane;
    public WelcomePageController controller;
    public ImageView sound;
    public Image unmute;
    public Button setting;
    public Button play;
    Parent parent;
    Scene scene;
    Stage stage;

    {
        controller = new WelcomePageController();
    }

    public void run(String[] args) {
        launch(args);
    }

    @FXML
    public void initialize() {
        if (!WelcomePageController.isIsMusicPlaying()) {
            sound.setImage(new Image("/pictures/musicIcon/mute.jpg"));
        }
    }

    @Override
    public void start(Stage primaryStage) {
        readData();
        controller.createMaps();
        try {
            parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/userInterface/fxml/Welcome.fxml")));
            primaryStage.setTitle("Pac-Man");
            Scene scene = new Scene(parent);
            primaryStage.setScene(scene);
            primaryStage.show();
            Media musicFile = new Media(Paths.get("src/main/resources/music/pacman_beginning.wav").toUri().toString());
            MediaPlayer mediaPlayer = new MediaPlayer(musicFile);
            WelcomePageController.setMedia(mediaPlayer);
            controller.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void switchToLoginScene() throws IOException {
        String address = "/userInterface/fxml/LoginUser.fxml";
        controller.moveToPage(address, profile, "login");
    }

    public void switchToRegisterScene() throws IOException {
        String address = "/userInterface/fxml/Register.fxml";
        controller.moveToPage(address, profile, "register");
    }


    public void switchToProfileScene() throws IOException {
        String address = "/userInterface/fxml/Profile.fxml";
        controller.moveToPage(address, profile, "profile");
    }


    public void switchToScoreBoard(MouseEvent event) throws IOException {
        parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/userInterface/fxml/ScoreBoard.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(parent, 700, 500);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToSetting() throws IOException {
        String address = "/userInterface/fxml/Setting.fxml";
        controller.moveToPage(address, setting, "setting");
    }

    public void switchToGame() throws IOException {
        controller.stop();
        String address = "/userInterface/fxml/GamePlay.fxml";
        controller.moveToPage(address, setting, "playGame");
    }

    public void exit() throws IOException {
        FileWriter writer = new FileWriter("G:/semister 2/java exercise/pac-man/src/main/resources/jsonfiles/Players.json");
        ArrayList<Player> allPlayers = Player.getAllPlayer();
        writer.write(new Gson().toJson(allPlayers));
        writer.close();
        FileWriter mapSaver = new FileWriter("G:/semister 2/java exercise/pac-man/src/main/resources/jsonfiles/Maps.json");
        ArrayList<Map> allMaps = Map.getAllMaps();
        mapSaver.write(new Gson().toJson(allMaps));
        mapSaver.close();
        FileWriter rankSaver = new FileWriter("G:/semister 2/java exercise/pac-man/src/main/resources/jsonfiles/Ranks.json");
        ArrayList<ScoreBoardContent> allRanks = ScoreBoardContent.getAllRank();
        rankSaver.write(new Gson().toJson(allRanks));
        rankSaver.close();
        System.exit(0);
    }

    public void readData() {
        try {
            String json = new String(Files.readAllBytes(Paths.get("G:/semister 2/java exercise/pac-man/src/main/resources/jsonfiles/Players.json")));
            ArrayList<Player> people;
            people = new Gson().fromJson(json,
                    new TypeToken<List<Player>>() {
                    }.getType()
            );
            Player.setAllPlayer(people);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            String json = new String(Files.readAllBytes(Paths.get("G:/semister 2/java exercise/pac-man/src/main/resources/jsonfiles/Maps.json")));
            ArrayList<Map> allMaps;
            allMaps = new Gson().fromJson(json,
                    new TypeToken<List<Map>>() {
                    }.getType()
            );
            Map.setAllPlayer(allMaps);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            String json = new String(Files.readAllBytes(Paths.get("G:/semister 2/java exercise/pac-man/src/main/resources/jsonfiles/Ranks.json")));
            ArrayList<ScoreBoardContent> allRanks;
            allRanks = new Gson().fromJson(json,
                    new TypeToken<List<ScoreBoardContent>>() {
                    }.getType()
            );
            ScoreBoardContent.setAllRank(allRanks);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void clickOnSound() {
        if (sound.getImage().equals(unmute)) {
            controller.stop();
            Image image = new Image("/pictures/musicIcon/mute.jpg");
            sound.setImage(image);
        } else {
            controller.play();
            sound.setImage(unmute);
        }
    }

}
