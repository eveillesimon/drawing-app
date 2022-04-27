package com.example.drawapp;

import android.graphics.Path;

public interface DrawnElement {
    public Path getPath();
    public int getColor();
    public float getThickness();
}
