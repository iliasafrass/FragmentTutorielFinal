package com.example.a707446.dominospizza;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by ilias afrass on 16/11/18
 **/
public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Thread chrono = new Thread() {
            public void run() {
                try {
                    sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    Intent i = new Intent("com.example.a707446.dominospizza.MAINACTIVITY");
                    startActivity(i);
                }
            }

        };
        chrono.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}

