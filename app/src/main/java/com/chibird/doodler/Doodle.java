package com.chibird.doodler;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Doodle extends AppCompatActivity{

    private boolean brushOpen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doodle);
        final DoodleView doodleView = (DoodleView)findViewById(R.id.doodleView);
        final ArrayList<View> brushSettings = new ArrayList<View>();

        // Adding all brush settings to array for easy visibility/invisibility changing.
        // Would be more efficient to put in a layout, but layout was acting wonky, so using this
        // workable solution for now.
        brushSettings.add(findViewById(R.id.brushSizeBar));
        brushSettings.add(findViewById(R.id.sizeLabel));
        brushSettings.add(findViewById(R.id.opacityBar));
        brushSettings.add(findViewById(R.id.opacityLabel));
        brushSettings.add(findViewById(R.id.rColor));
        brushSettings.add(findViewById(R.id.rBar));
        brushSettings.add(findViewById(R.id.gColor));
        brushSettings.add(findViewById(R.id.gBar));
        brushSettings.add(findViewById(R.id.bColor));
        brushSettings.add(findViewById(R.id.bBar));
        brushSettings.add(findViewById(R.id.backgroundBox));

        for (int i = 0; i < brushSettings.size(); i++) {
            brushSettings.get(i).setVisibility(View.INVISIBLE);
        }

        Button clearButton = (Button)findViewById(R.id.clearButton);
        clearButton.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View v) {
                doodleView.clear();

            }
        });

        ImageButton undoButton = (ImageButton)findViewById(R.id.undoButton);
        undoButton.setOnClickListener(new ImageButton.OnClickListener(){

            @Override
            public void onClick(View v) {
                doodleView.undo();
            }
        });

        ImageButton redoButton = (ImageButton)findViewById(R.id.redoButton);
        redoButton.setOnClickListener(new ImageButton.OnClickListener(){

            @Override
            public void onClick(View v) {
                doodleView.redo();
            }
        });

        Button randomBg = (Button)findViewById(R.id.randomBg);
        randomBg.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View v) {
                doodleView.randomize();

            }
        });

        Button brushButton = (Button)findViewById(R.id.brushButton);
        brushButton.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View v) {
                if(!brushOpen) {
                    for (int i = 0; i < brushSettings.size(); i++) {
                        brushSettings.get(i).setVisibility(View.VISIBLE);
                    }
                    brushOpen = true;
                } else {
                    for (int i = 0; i < brushSettings.size(); i++) {
                        brushSettings.get(i).setVisibility(View.INVISIBLE);
                    }
                    brushOpen = false;
                }
            }
        });

        // Change brush size
        SeekBar brushSizeBar = (SeekBar)findViewById(R.id.brushSizeBar);
        brushSizeBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar,
                                          int progress,
                                          boolean fromUser) {
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                doodleView.changeSize(seekBar.getProgress());
            }

        });

        // Change opacity
        SeekBar opacityBar = (SeekBar)findViewById(R.id.opacityBar);
        opacityBar.setProgress(100);
        opacityBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar,
                                          int progress,
                                          boolean fromUser) {
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                doodleView.changeOpacity(seekBar.getProgress());
            }

        });

        // Change R component of color
        SeekBar rBar = (SeekBar)findViewById(R.id.rBar);
        rBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar,
                                          int progress,
                                          boolean fromUser) {
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                TextView rColor = (TextView)findViewById(R.id.rColor);
                rColor.setText("R "+seekBar.getProgress());
                doodleView.changeColor(seekBar.getProgress(),-1,-1);
            }
        });

        // Change G component of color
        SeekBar gBar = (SeekBar)findViewById(R.id.gBar);
        gBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar,
                                          int progress,
                                          boolean fromUser) {
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                TextView gColor = (TextView)findViewById(R.id.gColor);
                gColor.setText("G "+seekBar.getProgress());
                doodleView.changeColor(-1,seekBar.getProgress(),-1);
            }
        });

        // Change B component of color
        SeekBar bBar = (SeekBar)findViewById(R.id.bBar);
        bBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar,
                                          int progress,
                                          boolean fromUser) {
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                TextView bColor = (TextView)findViewById(R.id.bColor);
                bColor.setText("B "+seekBar.getProgress());
                doodleView.changeColor(-1,-1,seekBar.getProgress());
            }
        });
    }
}
