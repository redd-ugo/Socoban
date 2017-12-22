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
        int dx=0,dy=0;
        if (direction.ordinal()<2) dx = FIELD_CELL_SIZE;
        else dy = FIELD_CELL_SIZE;
        if (direction.ordinal()%2==0){
            dx*=-1;
            dy*=-1;
        }
        Player player = gameObjects.getPlayer();

        //Can player smash into wall? Return true if so.
        if (checkWallCollision(player,direction)) return true;
        //Find box in which player can smash.
        Box collisionBox = getCollisionBox(player,gameObjects.getBoxes(),direction);

        //If no such box, return false.
        if (collisionBox == null) return false;

        //Will this box smash into any wall? If yes return true.
        if (checkWallCollision(collisionBox,direction)) return true;
        //If no - check 
        if (getCollisionBox(collisionBox,gameObjects.getBoxes(),direction)==null) {
            collisionBox.move(dx,dy);
            return false;
        }
        return true;
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
