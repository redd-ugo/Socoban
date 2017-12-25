package com.javarush.task.task34.task3410.model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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

        Set<Box> boxes = new HashSet<>();
        Set<Home> homes = new HashSet<>();

        int cellSize = Model.FIELD_CELL_SIZE;
        int halfCell = cellSize / 2;
        Player player = new Player(11 * cellSize / 2, 11 * cellSize / 2);
        level = level>60? level%60 : level;

        String requestLevel = "Maze: " + level;

        try (BufferedReader reader = new BufferedReader(new FileReader(levels.normalize().toAbsolutePath().toFile()))) {
            while (true) {
                String sLevel = reader.readLine();
                if (requestLevel.equals(sLevel.trim())) {
                    break;
                }
        }
        reader.readLine();
        String[] sWidth = reader.readLine().split(" ");
            String[] sHeight = reader.readLine().split(" ");

            int width = Integer.parseInt(sWidth[2]);
            int height = Integer.parseInt(sHeight[2]);
            reader.readLine();
            reader.readLine();
            reader.readLine();

            for (int y = 0; y < height; y++) {
                String line = reader.readLine();
                char[] chars = line.toCharArray();
                for (int x = 0; x < width; x++) {
                    char aChar = chars[x];

                    switch (aChar) {
                        case ' ':
                            break;
                        case 'X':
                            walls.add(new Wall(x * cellSize + halfCell, y * cellSize + halfCell));
                            break;
                        case '*':
                            boxes.add(new Box(x * cellSize + halfCell, y * cellSize + halfCell));
                            break;
                        case '.':
                            homes.add(new Home(x * cellSize + halfCell, y * cellSize + halfCell));
                            break;
                        case '&':
                            boxes.add(new Box(x * cellSize + halfCell, y * cellSize + halfCell));
                            homes.add(new Home(x * cellSize + halfCell, y * cellSize + halfCell));
                            break;
                        case '@':
                            player = new Player(x * cellSize + halfCell, y * cellSize + halfCell);
                            break;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        GameObjects gameObjects = new GameObjects(walls, boxes, homes, player);
        return gameObjects;
    }
}
