package com.smart.ludoclassic;

public class GridBox {
    float f48x;
    float f49y;
    Integer tag;

    public GridBox(float x, float y, int value) {
        this.f48x = x;
        this.f49y = y;
        this.tag = Integer.valueOf(value);
    }
    public float getX() {
        return this.f48x;
    }

    public void setX(float x) {
        this.f48x = x;
    }

    public float getY() {
        return this.f49y;
    }

    public void setY(float y) {
        this.f49y = y;
    }


}
