package com.example.kebunku;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.kebunku.R;

public class splash extends AppCompatActivity {
    private int Value = 0;
    private ProgressBar progressBar;
    private TextView persentase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        progressBar = findViewById(R.id.progressBar);
        persentase = findViewById(R.id.persentase);
        progressBar.setProgress(0);//progress dimulai dari 0 persen


        final Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                persentase.setText(String.valueOf(Value) + "%");
                if (Value == progressBar.getMax()) {
                    startActivity(new Intent(splash.this, login.class));
                    finish();
                }
                Value++;
            }
        };

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int waktu = 0; waktu <= progressBar.getMax(); waktu++) {
                        progressBar.setProgress(waktu);
                        handler.sendMessage(handler.obtainMessage());
                        Thread.sleep(25);
                    }
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        });
        thread.start();
    }
}



