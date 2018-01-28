package com.example.antonruban.notes.notes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.antonruban.notes.R;

public class SplashActivity extends Activity {

    private ImageView imageSplash;
    Animation animation_1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        imageSplash = (ImageView) findViewById(R.id.splashImage);
         animation_1 = AnimationUtils.loadAnimation(getBaseContext(),R.anim.scale);

        imageSplash.startAnimation(animation_1);
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                Intent mainIntent = new Intent(SplashActivity.this,MainActivity.class);
                startActivity(mainIntent);
                finish();
            }
        }, 2000);
    }
}
