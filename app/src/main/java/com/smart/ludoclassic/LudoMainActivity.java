package com.smart.ludoclassic;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

public class LudoMainActivity extends SideMenuActivity {

    Button btnMultiplayer;
    Button btnTwoplayer;
    Button btnVsCompTwoplayer;
    Button btnVsComputer;
    private AdView mAdView;
    private InterstitialAd mInterstitialAdMob;
    private Activity currentActivity;

    class C03801 implements OnClickListener {
        C03801() {
        }

        public void onClick(View v) {
            startActivity(new Intent(LudoMainActivity.this, LudoMultiplayerActivity.class));
            showAdmobInterstitial();
        }
    }

    class C03812 implements OnClickListener {
        C03812() {
        }

        public void onClick(View v) {
           startActivity(new Intent(LudoMainActivity.this, LudoVsCompActivity.class));
           showAdmobInterstitial();
        }
    }

    class C03823 implements OnClickListener {
        C03823() {
        }

        public void onClick(View v) {
            startActivity(new Intent(LudoMainActivity.this, LudoTwoplayerActivity.class));
            showAdmobInterstitial();

        }
    }

    class C03834 implements OnClickListener {
        C03834() {
        }

        public void onClick(View v) {
          startActivity(new Intent(LudoMainActivity.this, LudoVsCompTwoplayerActivity.class));
          showAdmobInterstitial();

        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StaticVariables.initSoundPool(getApplicationContext());
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        FrameLayout contentFrameLayout = (FrameLayout)findViewById(R.id.content_frame);
        // inflating contentFrameLayout with the current activity layout.
        LayoutInflater.from(this).inflate(R.layout.ludo_activity_main,contentFrameLayout);
        currentActivity = this;



        BannerAd();
        initAdmobFullAd();
        loadAdmobAd();

        btnMultiplayer = (Button) findViewById(R.id.btnLudoMultiplayer);
        btnMultiplayer.setOnClickListener(new C03801());
        btnVsComputer = (Button) findViewById(R.id.btnLudoVsComp);
        btnVsComputer.setOnClickListener(new C03812());
        btnTwoplayer = (Button) findViewById(R.id.btnLudoTwoPlayer);
        btnTwoplayer.setOnClickListener(new C03823());
        btnVsCompTwoplayer = (Button) findViewById(R.id.btnLudoVsCompTwoPlayer);
        btnVsCompTwoplayer.setOnClickListener(new C03834());

        mForwardLayout =  findViewById(R.id.main_layout);


        findViewById(R.id.menuIcon).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                openCloseMenu();
            }
        });

    }

    private  void BannerAd(){
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

    }

    public void initAdmobFullAd() {
        mInterstitialAdMob = new com.google.android.gms.ads.InterstitialAd(this);
        mInterstitialAdMob.setAdUnitId(getResources().getString(R.string.ad_interstitial));
        mInterstitialAdMob.setAdListener(new com.google.android.gms.ads.AdListener() {
            @Override
            public void onAdClosed() {
                loadAdmobAd();

            }

            @Override
            public void onAdLoaded() {

            }

            @Override
            public void onAdOpened() {
            }
        });
    }

    public void loadAdmobAd() {
        mInterstitialAdMob.loadAd(new AdRequest.Builder().build());
    }

    public void showAdmobInterstitial() {
        if (mInterstitialAdMob != null && mInterstitialAdMob.isLoaded()) {
            mInterstitialAdMob.show();
        }
    }


}
