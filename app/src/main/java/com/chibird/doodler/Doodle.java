package com.chibird.doodler;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class Doodle extends AppCompatActivity{

    private boolean brushOpen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doodle);
        final DoodleView doodleView = (DoodleView)findViewById(R.id.doodleView);
        findViewById(R.id.brushSizeBar).setVisibility(View.INVISIBLE);
        findViewById(R.id.sizeLabel).setVisibility(View.INVISIBLE);
        findViewById(R.id.opacityBar).setVisibility(View.INVISIBLE);
        findViewById(R.id.opacityLabel).setVisibility(View.INVISIBLE);
        findViewById(R.id.rColor).setVisibility(View.INVISIBLE);
        findViewById(R.id.rBar).setVisibility(View.INVISIBLE);
        findViewById(R.id.gColor).setVisibility(View.INVISIBLE);
        findViewById(R.id.gBar).setVisibility(View.INVISIBLE);
        findViewById(R.id.bColor).setVisibility(View.INVISIBLE);
        findViewById(R.id.bBar).setVisibility(View.INVISIBLE);
        findViewById(R.id.backgroundBox).setVisibility(View.INVISIBLE);

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
                    findViewById(R.id.brushSizeBar).setVisibility(View.VISIBLE);
                    findViewById(R.id.sizeLabel).setVisibility(View.VISIBLE);
                    findViewById(R.id.opacityBar).setVisibility(View.VISIBLE);
                    findViewById(R.id.opacityLabel).setVisibility(View.VISIBLE);
                    findViewById(R.id.rColor).setVisibility(View.VISIBLE);
                    findViewById(R.id.rBar).setVisibility(View.VISIBLE);
                    findViewById(R.id.gColor).setVisibility(View.VISIBLE);
                    findViewById(R.id.gBar).setVisibility(View.VISIBLE);
                    findViewById(R.id.bColor).setVisibility(View.VISIBLE);
                    findViewById(R.id.bBar).setVisibility(View.VISIBLE);
                    findViewById(R.id.backgroundBox).setVisibility(View.VISIBLE);
                    brushOpen = true;
                } else {
                    findViewById(R.id.brushSizeBar).setVisibility(View.INVISIBLE);
                    findViewById(R.id.sizeLabel).setVisibility(View.INVISIBLE);
                    findViewById(R.id.opacityBar).setVisibility(View.INVISIBLE);
                    findViewById(R.id.opacityLabel).setVisibility(View.INVISIBLE);
                    findViewById(R.id.rColor).setVisibility(View.INVISIBLE);
                    findViewById(R.id.rBar).setVisibility(View.INVISIBLE);
                    findViewById(R.id.gColor).setVisibility(View.INVISIBLE);
                    findViewById(R.id.gBar).setVisibility(View.INVISIBLE);
                    findViewById(R.id.bColor).setVisibility(View.INVISIBLE);
                    findViewById(R.id.bBar).setVisibility(View.INVISIBLE);
                    findViewById(R.id.backgroundBox).setVisibility(View.INVISIBLE);
                    brushOpen = false;
                }
            }
        });

        SeekBar brushSizeBar = (SeekBar)findViewById(R.id.brushSizeBar);
        brushSizeBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar,
                                          int progress,
                                          boolean fromUser) {
                //Toast.makeText(getApplicationContext(),"HI"+Integer.toString(progress),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                doodleView.changeSize(seekBar.getProgress());
            }

        });


        SeekBar opacityBar = (SeekBar)findViewById(R.id.opacityBar);
        opacityBar.setProgress(100);
        opacityBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar,
                                          int progress,
                                          boolean fromUser) {
                //Toast.makeText(getApplicationContext(),"HI"+Integer.toString(progress),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                doodleView.changeOpacity(seekBar.getProgress());
            }

        });

        SeekBar rBar = (SeekBar)findViewById(R.id.rBar);
        rBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar,
                                          int progress,
                                          boolean fromUser) {
                //Toast.makeText(getApplicationContext(),"HI"+Integer.toString(progress),Toast.LENGTH_SHORT).show();
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

        SeekBar gBar = (SeekBar)findViewById(R.id.gBar);
        gBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar,
                                          int progress,
                                          boolean fromUser) {
                //Toast.makeText(getApplicationContext(),"HI"+Integer.toString(progress),Toast.LENGTH_SHORT).show();
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

        SeekBar bBar = (SeekBar)findViewById(R.id.bBar);
        bBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar,
                                          int progress,
                                          boolean fromUser) {
                //Toast.makeText(getApplicationContext(),"HI"+Integer.toString(progress),Toast.LENGTH_SHORT).show();
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
