package com.example.drawapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.Path;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Path path = new Path();
    private int color = Color.BLACK;
    private float thickness = 50;

    private int[] button = {R.id.redButton, R.id.greenButton, R.id.blueButton, R.id.yellowButton, R.id.blackButton};
    private int[] colorButton = {Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW, Color.BLACK};

    private void selectButton(int id) {
        for (int i = 0; i < button.length; i++) {
            Button b = findViewById(button[i]);
            if (id == button[i]) {
                b.setBackgroundColor(colorButton[i]);
                this.color = colorButton[i];
            } else {
                b.setBackgroundColor(Color.GRAY);
            }
        }
    }

    public void setThickness(float thickness) {
        this.thickness = thickness;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        DrawingView drawingView = findViewById(R.id.drawing_view);

        selectButton(R.id.blackButton);

        for (int i = 0; i < button.length; i++) {
            int id = button[i];
            Button b = findViewById(id);
            b.setOnClickListener(v -> {
                selectButton(id);
            });
        }

        SeekBar seekBar = findViewById(R.id.thickBar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressChangedValue = 0;

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressChangedValue = progress;
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                MainActivity.this.thickness = progressChangedValue;
            }
        });

        FloatingActionButton undoButton = findViewById(R.id.undoButton);
        undoButton.setOnClickListener(v -> {
                drawingView.undo();
                drawingView.invalidate();
                });

        drawingView.setOnTouchListener((view, event)->{
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    this.path = new Path();
                    this.path.moveTo(event.getX(), event.getY());
                    drawingView.addModel(new DrawnLine(this.color, this.thickness, this.path));
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