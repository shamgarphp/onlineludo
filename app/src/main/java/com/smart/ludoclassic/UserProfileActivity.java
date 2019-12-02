package com.smart.ludoclassic;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class UserProfileActivity extends AppCompatActivity implements View.OnClickListener {

    TextView usrname,contactNo,organization,position,employeeType,deptCode;
    RelativeLayout backNav;
    private SharedPreferences pref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
             init();
    }

    public void init(){

//        usrname = findViewById(R.id.usrname);
//        contactNo = findViewById(R.id.contactNo);
//        organization = findViewById(R.id.organization);
//        position = findViewById(R.id.position);
//        employeeType = findViewById(R.id.employeeType);
//        deptCode = findViewById(R.id.deptCode);
        backNav = findViewById(R.id.back);



        backNav.setOnClickListener(this);

//        initializeViews();

    }

    public void initializeViews(){

        usrname.setText(pref.getString("name",""));
        contactNo.setText(pref.getString("mobile",""));
        organization.setText(pref.getString("organization",""));
        position.setText(pref.getString("position",""));
        employeeType.setText(pref.getString("employeetype",""));
        deptCode.setText(pref.getString("deptcode",""));

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.back:
                Intent i = new Intent(getApplicationContext(),LudoMainActivity.class);
                startActivity(i);
                finish();
                break;
        }
    }


    @Override
    public void onBackPressed() {
        Intent i = new Intent(getApplicationContext(),LudoMainActivity.class);
        startActivity(i);
        finish();
    }
}
