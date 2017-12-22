package com.javarush.task.task34.task3410.model;

import java.awt.*;

public class Wall extends CollisionObject {
    public Wall(int x, int y) {
        super(x, y);
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(new Color(104,10,2));
        int leftUpperCornerX = getX() - getWidth() / 2;
        int leftUpperCornerY = getY() - getHeight() / 2;
        graphics.fillRect(leftUpperCornerX,leftUpperCornerY,getWidth(),getHeight());
        int lineY1 = getY() - getHeight()/4;
        int lineY2 = getY() + getHeight()/4;
        int lineY3 = getY() + getHeight()/2;
        int lineX = getX() + getWidth()/2;
        graphics.setColor(Color.lightGray);
        graphics.drawRect(leftUpperCornerX,lineY1,getWidth(),getY()-lineY1);
        graphics.drawRect(leftUpperCornerX,lineY2,getWidth(),lineY3-lineY2);
        graphics.drawLine(leftUpperCornerX,leftUpperCornerY,lineX,leftUpperCornerY);
        graphics.drawLine(getX(),leftUpperCornerY,getX(),lineY1);
        graphics.drawLine(getX(),getY(),getX(),lineY2);

    }
}
