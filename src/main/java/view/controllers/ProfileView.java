package view.controllers;

import controller.ProfileController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import model.LoginUser;

import java.io.IOException;
import java.util.Optional;

public class ProfileView {
    public Button back;
    public Button records;
    public Button changePass;
    public Button deleteAccount;
    public ImageView redGhost;
    public ImageView pinkGhost;
    public ImageView blueGhost;
    public AnchorPane anchorPane;
    public Label error;
    ProfileController controller;


    {
        controller = new ProfileController();
    }

    @FXML
    public void initialize() {
        if (LoginUser.getPlayer() == null) {
            error.setStyle("-fx-border-color: red ; -fx-border-width: 3 ; -fx-text-fill: red ; -fx-font-weight: bold; -fx-font-size: 14");
            error.setText("no user login yet!!!");
            records.setDisable(true);
            changePass.setDisable(true);
            deleteAccount.setDisable(true);
        } else {
            records.setDisable(false);
            changePass.setDisable(false);
            deleteAccount.setDisable(false);
        }
    }

    public void mouseDragEnterChangePass() {
        controller.changePasswordAnimationMouseEnter(pinkGhost);
    }

    public void mouseExitChangePassButt() {
        controller.changePasswordAnimationMouseExit(pinkGhost);
    }

    public void mouseDragEnterDelete() {
        controller.deleteMouseEnter(redGhost);
    }

    public void mouseExitDelete() {
        controller.deleteMouseExit(redGhost);
    }

    public void mouseDragEnterScore() {
        controller.scoresMouseEnter(blueGhost);
    }

    public void mouseExitScore() {
        controller.scoreMouseExit(blueGhost);
    }

    public void switchToWelcomeScene() throws IOException {
        String address = "/userInterface/fxml/Welcome.fxml";
        controller.moveToPage(address , error, "welcome");
    }

    public void goToScorePage() {
    }

    public void goToChangePassPage() throws IOException {
        String address = "/userInterface/fxml/Change.fxml";
        controller.moveToPage(address , error, "welcome");
    }

    public void showAlertBox(ActionEvent event) throws IOException {
        ButtonType sure = new ButtonType("im sure", ButtonBar.ButtonData.OK_DONE);
        ButtonType refuse = new ButtonType("cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION, "", sure, refuse);
        confirmation.setTitle("DELETE ACCOUNT");
        confirmation.setContentText("Are You sure you want to delete your account");
        confirmation.initOwner(((Node) event.getSource()).getScene().getWindow());
        Optional<ButtonType> result = confirmation.showAndWait();
        if (result.get() == sure) {
            controller.deleteAccount();
            switchToWelcomeScene();
        }
    }
}
