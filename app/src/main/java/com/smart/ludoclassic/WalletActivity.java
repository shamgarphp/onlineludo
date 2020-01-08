package com.smart.ludoclassic;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ListView;

public class WalletActivity extends SideMenuActivity {

    ListView transList;
    Activity currentActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout contentFrameLayout = (FrameLayout)findViewById(R.id.content_frame);
        // inflating contentFrameLayout with the current activity layout.
        LayoutInflater.from(this).inflate(R.layout.activity_wallet,contentFrameLayout);
        currentActivity = this;
        mForwardLayout =  findViewById(R.id.main_layout);

        transList = findViewById(R.id.transList);

        TransListViewAdapter transListViewAdapter = new TransListViewAdapter(this);
        transList.setAdapter(transListViewAdapter);

        findViewById(R.id.menuIcon).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCloseMenu();
                /* https://github.com/ravi8x/Android-E-Commerce-PayTM*/
            }
        });

    }
}
