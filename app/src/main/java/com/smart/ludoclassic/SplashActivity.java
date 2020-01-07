package com.smart.ludoclassic;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    SharedPreferences sharedpreferences;
    boolean login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

        sharedpreferences = getSharedPreferences("Userdetails",
                Context.MODE_PRIVATE);

        login = sharedpreferences.getBoolean("islogin", false);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (login) {
                    Intent mainIntent = new Intent(getApplicationContext(),LudoMainActivity.class);
                    startActivity(mainIntent);
                    finish();
                }else {
                    startActivity(new Intent(SplashActivity.this, SignInActivity.class));
                    finish();
                }
            }
        }, 2000);
    }
}
