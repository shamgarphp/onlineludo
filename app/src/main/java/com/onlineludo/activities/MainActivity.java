package com.onlineludo.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.onlineludo.R;
import com.onlineludo.adapters.CouponsListAdapter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView cuponList = findViewById(R.id.cuponsList);
        CouponsListAdapter adapter = new CouponsListAdapter(this);
        cuponList.setAdapter(adapter);


    }
}
