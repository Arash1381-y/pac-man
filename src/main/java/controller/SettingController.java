package controller;

import model.Game;

public class SettingController extends Controller {
    public void reduceHealth() {
        Game.reduceHealth();
    }

    public void increaseHealth() {
        Game.increaseHealth();
    }
}
