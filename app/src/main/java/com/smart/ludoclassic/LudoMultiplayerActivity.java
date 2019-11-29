package com.smart.ludoclassic;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class LudoMultiplayerActivity extends Activity {

    protected static Boolean play;
    private AdView mAdView;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StaticVariables.initSoundPool(getApplicationContext());
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.ludo_activity_multiplayer);
        play = Boolean.TRUE;

        BannerAd();

    }

    private void BannerAd() {
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

    }


    public void onBackPressed() {
        super.onBackPressed();
    }

}
