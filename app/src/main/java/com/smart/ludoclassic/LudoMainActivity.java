package com.smart.ludoclassic;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.paytm.pgsdk.PaytmClientCertificate;
import com.paytm.pgsdk.PaytmOrder;
import com.paytm.pgsdk.PaytmPGService;
import com.paytm.pgsdk.PaytmPaymentTransactionCallback;

import java.util.HashMap;
import java.util.Map;

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
        if (ContextCompat.checkSelfPermission(LudoMainActivity.this, Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(LudoMainActivity.this, new String[]{Manifest.permission.READ_SMS, Manifest.permission.RECEIVE_SMS}, 101);
        }





        // inPassword is the password for client side certificate
        //inFileName is the file name of client side certificate

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
//                openCloseMenu();
               /* https://github.com/ravi8x/Android-E-Commerce-PayTM*/
                PaytmPGService Service = PaytmPGService.getProductionService();

                Map<String, String> paramMap = new HashMap<>();
                paramMap.put( "MID" , "rxazcv89315285244163");
// Key in your staging and production MID available in your dashboard
                paramMap.put( "ORDER_ID" , "order1");
                paramMap.put( "CUST_ID" , "cust123");
                paramMap.put( "MOBILE_NO" , "7777777777");
                paramMap.put( "EMAIL" , "username@emailprovider.com");
                paramMap.put( "CHANNEL_ID" , "WAP");
                paramMap.put( "TXN_AMOUNT" , "100.12");
                paramMap.put( "WEBSITE" , "WEBSTAGING");
// This is the staging value. Production value is available in your dashboard
                paramMap.put( "INDUSTRY_TYPE_ID" , "Retail");
// This is the staging value. Production value is available in your dashboard
                paramMap.put( "CALLBACK_URL", "https://securegw-stage.paytm.in/theia/paytmCallback?ORDER_ID=order1");
                paramMap.put( "CHECKSUMHASH" , "w2QDRMgp1234567JEAPCIOmNgQvsi+BhpqijfM9KvFfRiPmGSt3Ddzw+oTaGCLneJwxFFq5mqTMwJXdQE2EzK4px2xruDqKZjHupz9yXev4=");
                PaytmOrder Order = new PaytmOrder((HashMap<String, String>) paramMap);
                BannerAd();
                initAdmobFullAd();
                loadAdmobAd();
                Service.initialize(Order, null);
                Service.startPaymentTransaction(LudoMainActivity.this, true, true, new PaytmPaymentTransactionCallback() {
                    /*Call Backs*/
                    public void someUIErrorOccurred(String inErrorMessage) {
                        /*Display the error message as below */
                        Toast.makeText(getApplicationContext(), "UI Error " + inErrorMessage , Toast.LENGTH_LONG).show();
                    }
                    public void onTransactionResponse(Bundle inResponse) {
                        Toast.makeText(getApplicationContext(), "Payment Transaction response " + inResponse.toString(), Toast.LENGTH_LONG).show();
                    }
                    public void networkNotAvailable() {
                        /*Display the message as below */
                        Toast.makeText(getApplicationContext(), "Network connection error: Check your internet connectivity", Toast.LENGTH_LONG).show();
                    }
                    public void clientAuthenticationFailed(String inErrorMessage) {
                        /*Display the message as below */
                        Toast.makeText(getApplicationContext(), "Authentication failed: Server error" + inErrorMessage.toString(), Toast.LENGTH_LONG).show();
                    }
                    public void onErrorLoadingWebPage(int iniErrorCode, String inErrorMessage, String inFailingUrl) {
                        /*Display the message as below */
                        Toast.makeText(getApplicationContext(), "Unable to load webpage " + inErrorMessage.toString(), Toast.LENGTH_LONG).show();
                    }
                    public void onBackPressedCancelTransaction() {
                        /*Display the message as below */
                        Toast.makeText(getApplicationContext(), "Transaction cancelled" , Toast.LENGTH_LONG).show();
                    }
                    public void onTransactionCancel(String inErrorMessage, Bundle inResponse) {
                        Toast.makeText(getApplicationContext(), "Payment Transaction response " + inResponse.toString(), Toast.LENGTH_LONG).show();

                    }
                });
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
