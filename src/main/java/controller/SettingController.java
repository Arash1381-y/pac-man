package controller;

import model.GameProp;

public class SettingController extends Controller {
    public void reduceHealth() {
        GameProp.reduceHealth();
    }

    public void increaseHealth() {
        GameProp.increaseHealth();
    }
}
