package com.javarush.task.task34.task3410.controller;

import com.javarush.task.task34.task3410.model.Direction;
import com.javarush.task.task34.task3410.model.GameObjects;
import com.javarush.task.task34.task3410.model.Model;
import com.javarush.task.task34.task3410.view.View;


public class Controller implements EventListener {
    public Controller() {
        this.model = new Model();
        model.restart();
        this.view = new View(this);
        this.view.init();


    }
    private View view;
    private Model model;

    public static void main(String[] args) {
        Controller controller = new Controller();
    }

    @Override
    public void move(Direction direction) {

    }

    @Override
    public void restart() {

    }

    @Override
    public void startNextLevel() {

    }

    @Override
    public void levelCompleted(int level) {

    }

    public GameObjects getGameObjects(){
        return this.model.getGameObjects();
    }
}