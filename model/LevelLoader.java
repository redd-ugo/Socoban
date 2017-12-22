package com.javarush.task.task34.task3410.model;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class LevelLoader {
    private Path levels;

    public LevelLoader(Path levels) {
        this.levels = levels;
    }

    public GameObjects getLevel(int level) {
        Set<Wall> walls = new HashSet<>();
        walls.add(new Wall(3*Model.FIELD_CELL_SIZE / 2, Model.FIELD_CELL_SIZE / 2));
        walls.add(new Wall(5*Model.FIELD_CELL_SIZE / 2, Model.FIELD_CELL_SIZE / 2));
        walls.add(new Wall(Model.FIELD_CELL_SIZE / 2, Model.FIELD_CELL_SIZE / 2));
        walls.add(new Wall(Model.FIELD_CELL_SIZE / 2, 3*Model.FIELD_CELL_SIZE / 2));

        Set<Box> boxes = new HashSet<>();
        boxes.add(new Box(5*Model.FIELD_CELL_SIZE / 2, 7*Model.FIELD_CELL_SIZE / 2));

        Set<Home> homes = new HashSet<>();
        homes.add(new Home(3*Model.FIELD_CELL_SIZE / 2, 3*Model.FIELD_CELL_SIZE / 2));

        Player player = new Player(11*Model.FIELD_CELL_SIZE / 2, 11*Model.FIELD_CELL_SIZE / 2);

        GameObjects gameObjects = new GameObjects(walls, boxes, homes, player);
        return gameObjects;
    }
}
