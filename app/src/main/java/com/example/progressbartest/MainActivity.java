package com.example.progressbartest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ProgressBar pgsBar;
    private TextView pgTextView;
    private Button btnProgress;
    private int i = 0;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pgsBar = (ProgressBar) findViewById(R.id.pb_bar);
        pgTextView = (TextView) findViewById(R.id.txt_pbBar);
        btnProgress = (Button) findViewById(R.id.btn_progress);

        btnProgress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = pgsBar.getProgress();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (i < 100){
                            i += 1;
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    pgsBar.setProgress(i);
                                    pgTextView.setText(i+"/"+pgsBar.getMax());
                                }
                            });

                            try{
                                Thread.sleep(100);
                            }catch (InterruptedException e){
                                e.printStackTrace();
                            }
                        }
                    }
                }).start();
            }
        });
    }
}
