package com.example.drawapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.Path;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Path path = new Path();
    int color = Color.YELLOW;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);






        Path path = new Path();
        path.moveTo(100, 100);
        path.lineTo(200, 100);
        path.lineTo(100, 200);
        DrawnLine line = new DrawnLine(R.color.black, 10, path);
        ArrayList<DrawnLine> lines = new ArrayList<>();
        lines.add(line);

        DrawingView drawingView = findViewById(R.id.drawing_view);
        drawingView.setModel(lines);
        drawingView.invalidate();


        drawingView.setOnTouchListener((view, event)->{
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    this.path = new Path();
                    this.path.moveTo(event.getX(), event.getY());
                    drawingView.addModel(new DrawnLine(this.color, 100, this.path));
                    drawingView.invalidate();
                    break;
                case MotionEvent.ACTION_MOVE:
                    this.path.lineTo(event.getX(), event.getY());
                    drawingView.invalidate();
                    break;
            }
            view.performClick();
            return true;
        });
    }

}