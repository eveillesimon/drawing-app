package com.example.drawapp;

import android.graphics.Path;

public class DrawnLine implements DrawnElement {
    private int color;
    private float thickness;
    private Path path;

    public DrawnLine(int color, float thickness, Path path) {
        this.color = color;
        this.thickness = thickness;
        this.path = path;
    }

    public Path getPath() {
        return path;
    }

    public int getColor() {
        return color;
    }

    public float getThickness() {
        return thickness;
    }
}
