package com.example.tunetown;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import android.content.Intent;
import androidx.core.view.WindowInsetsCompat;
import android.os.Bundle;
import android.os.Handler;

@SuppressLint("CustomSplashScreen")
public class SplashActivity extends AppCompatActivity {
    private static final int SPLASH_SCREEN_DELAY = 1000;//we use delay of 1s
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splash);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);


            new Handler().postDelayed(() -> {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }, SPLASH_SCREEN_DELAY);

            return insets;
        });
    }
}