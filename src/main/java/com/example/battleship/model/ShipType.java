package com.example.battleship.model;

import javafx.scene.shape.Rectangle;

public class ShipType {
    private final int size;
    private final Rectangle rectangle;

    public ShipType(int size, Rectangle rectangle) {
        this.size = size;
        this.rectangle = rectangle;
    }

    public int getSize() {
        return size;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }
    public ShipType copy(){
        Rectangle rectangle1 = new Rectangle(rectangle.getWidth(), rectangle.getHeight(), rectangle.getFill());
        rectangle1.setStyle(rectangle1.getStyle());
        return new ShipType(this.size, rectangle1);
    }
}
