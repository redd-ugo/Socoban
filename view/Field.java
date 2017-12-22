package com.javarush.task.task34.task3410.view;

import com.javarush.task.task34.task3410.controller.EventListener;
import com.javarush.task.task34.task3410.model.Direction;
import com.javarush.task.task34.task3410.model.GameObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Set;

public class Field extends JPanel {
    private View view;
    private EventListener eventListener;



    public Field(View view){
        //super();
        this.view = view;
        KeyHandler keyHandler = new KeyHandler(this);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
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
    public class KeyHandler extends KeyAdapter{
        private Field field;

        public KeyHandler(Field field) {
            this.field = field;
        }

        @Override
        public void keyPressed(KeyEvent e) {
            //Debug
            //System.out.println(e.getKeyCode());
            switch (e.getKeyCode()){
                case KeyEvent.VK_LEFT : field.eventListener.move(Direction.LEFT);
                    break;
                case KeyEvent.VK_RIGHT : field.eventListener.move(Direction.RIGHT);
                    break;
                case KeyEvent.VK_UP : field.eventListener.move(Direction.UP);
                    break;
                case KeyEvent.VK_DOWN : field.eventListener.move(Direction.DOWN);
                    break;
                case KeyEvent.VK_R : field.eventListener.restart();
                    break;
            }
            super.keyPressed(e);
        }
    }
}
