package com.smart.ludoclassic;

import android.graphics.drawable.Drawable;

public class DrawableMarker {
    int colMarker;
    Drawable drawableMarker;

    public DrawableMarker(Drawable drawableMarker, int colMarker) {
        this.drawableMarker = drawableMarker;
        this.colMarker = colMarker;
    }

    public Drawable getDrawableMarker() {
        return this.drawableMarker;
    }

    public void setDrawableMarker(Drawable drawableMarker) {
        this.drawableMarker = drawableMarker;
    }

    public int getColMarker() {
        return this.colMarker;
    }

    public void setColMarker(int colMarker) {
        this.colMarker = colMarker;
    }
}
