package com.kita.aplikasisupplier;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

public class SplashScreen extends AppCompatActivity {

    private static int SPLASH_SCREEN_TIMEOUT = 2000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_splash_screen);
        Animation fadeOut = new AlphaAnimation(1, 0);
        fadeOut.setInterpolator(new AccelerateDecelerateInterpolator());
        fadeOut.setStartOffset(500);
        fadeOut.setDuration(1800);
        ImageView image =  findViewById(R.id.opening);

        image.setAnimation(fadeOut);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreen.this, Satu.class);
                startActivity(intent);
                finish();

            }
        },SPLASH_SCREEN_TIMEOUT);
    }
}