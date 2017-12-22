package com.javarush.task.task34.task3410.model;

import com.javarush.task.task34.task3410.controller.EventListener;

import java.nio.file.Paths;
import java.util.Set;


public class Model {
    public static final int FIELD_CELL_SIZE = 20;
    private EventListener eventListener;
    private GameObjects gameObjects;
    private int currentLevel = 1;
    private LevelLoader levelLoader = new LevelLoader(Paths.get("..\\res\\levels.txt"));

    public void setEventListener(EventListener eventListener) {
        this.eventListener = eventListener;
    }
    public GameObjects getGameObjects(){
        return gameObjects;
    }
    public void restartLevel(int level){
        this.gameObjects = levelLoader.getLevel(level);
    }

    public void restart(){
        restartLevel(currentLevel);
    }

    public void startNextLevel(){
        restartLevel(++currentLevel);
    }

    public void move(Direction direction) {
        Player player = gameObjects.getPlayer();
        /*int dx=0,dy=0;
        if (direction.ordinal()<2) dx = FIELD_CELL_SIZE;
        else dy = FIELD_CELL_SIZE;
        if (direction.ordinal()%2==0){
            dx*=-1;
            dy*=-1;
        }*/
        //debug
        //System.out.println("check collisions");
        if (checkWallCollision(player,direction)) return;
        //debug
        //System.out.println("no wall collision");
        if (checkBoxCollisionAndMoveIfAvaliable(direction)) return;
        //debug
        //System.out.println("no box collision");
        switch (direction) {
            case LEFT:
                player.move(-FIELD_CELL_SIZE, 0);
                break;
            case RIGHT:
                player.move(FIELD_CELL_SIZE, 0);
                break;
            case UP:
                player.move(0, -FIELD_CELL_SIZE);
                break;
            case DOWN:
                player.move(0, FIELD_CELL_SIZE);
        }

        checkCompletion();

    }
    public boolean checkWallCollision(CollisionObject gameObject, Direction direction){
        for (Wall wall:gameObjects.getWalls()){
            if (gameObject.isCollision(wall,direction)) return true;
        }
        return false;
    }
    public boolean checkBoxCollisionAndMoveIfAvaliable(Direction direction){
        Player player = gameObjects.getPlayer();
        GameObject stoped = null;
        for (GameObject gameObject : gameObjects.getAll()) {
            if (!(gameObject instanceof Player) && !(gameObject instanceof Home) && player.isCollision(gameObject, direction)) {
                stoped = gameObject;
            }
        }

        if ((stoped == null)) {
            return false;
        }

        if (stoped instanceof Box) {
            Box stopedBox = (Box) stoped;
            if (checkWallCollision(stopedBox, direction)) {
                return true;
            }
            for (Box box : gameObjects.getBoxes()) {
                if (stopedBox.isCollision(box, direction)) {
                    return true;
                }
            }

            switch (direction) {
                case LEFT:
                    stopedBox.move(-FIELD_CELL_SIZE, 0);
                    break;
                case RIGHT:
                    stopedBox.move(FIELD_CELL_SIZE, 0);
                    break;
                case UP:
                    stopedBox.move(0, -FIELD_CELL_SIZE);
                    break;
                case DOWN:
                    stopedBox.move(0, FIELD_CELL_SIZE);
            }
        }
        return false;
    }

    private Box getCollisionBox(CollisionObject collisionObject,Set<Box> boxes, Direction direction){
        for(Box box: boxes){
            if (collisionObject.isCollision(box,direction)){
                return box;
            }
        }
        return null;
    }

    public void checkCompletion(){
        int count = gameObjects.getHomes().size();
        for(Home home:gameObjects.getHomes()){
            for (Box box:gameObjects.getBoxes()){
                if (home.getX()==box.getX() && home.getY() == box.getY()){
                    count--;
                }
            }
        }
        if (count==0) eventListener.levelCompleted(currentLevel);
    }
}
