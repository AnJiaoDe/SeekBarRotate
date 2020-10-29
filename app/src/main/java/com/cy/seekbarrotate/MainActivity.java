package com.cy.seekbarrotate;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<10;i++){
                    System.out.println("LOGi"+i);
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        for(int i=0;i<10;i++){
                            System.out.println("LOGrunOnUiThread"+i);
                        }
                    }
                });

            }
        }).start();
        for(int i=0;i<200;i++){
            System.out.println("LOGkkkkkk"+i);
        }


    }
}
