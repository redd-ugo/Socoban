package com.javarush.task.task34.task3410.view;

import com.javarush.task.task34.task3410.controller.EventListener;
import com.javarush.task.task34.task3410.model.GameObject;

import javax.swing.*;
import java.awt.*;
import java.util.Set;

public class Field extends JPanel {
    private View view;
    private EventListener eventListener;

    public Field(View view){
        super();
        this.view = view;
    }

    public void paint(Graphics g){
        g.setColor(Color.black);
        g.fillRect(0,0,getWidth(),getHeight());
        Set<GameObject> gameObjectSet = view.getGameObjects().getAll();
        for (GameObject gameObject: gameObjectSet){
            gameObject.draw(g);
        }
    }

    public void setEventListener(EventListener eventListener) {
        this.eventListener = eventListener;
    }
}
