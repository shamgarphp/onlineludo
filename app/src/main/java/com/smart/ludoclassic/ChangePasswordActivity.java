package com.smart.ludoclassic;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;


public class ChangePasswordActivity extends SideMenuActivity implements View.OnClickListener  {

    RelativeLayout menuIcon;
    Activity currentActivity;
    Button submitBtn;
    EditText oldpwdEt,newpwdEt,cpwdEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout contentFrameLayout = findViewById(R.id.content_frame);
        // inflating contentFrameLayout with the current activity layout.
        LayoutInflater.from(this).inflate(R.layout.activity_change_password,contentFrameLayout);
        currentActivity = this;

        /*Initializing Views*/
        init();

    }
    /**
     * Here we initialize all the views ,layouts,buttons
     */
    public void init(){


        menuIcon  =  findViewById(R.id.menuIcon);

        mForwardLayout =  findViewById(R.id.main_layout);
        oldpwdEt = findViewById(R.id.oldpwdEt);
        newpwdEt = findViewById(R.id.newpwdEt);
        cpwdEt   = findViewById(R.id.cpwdEt);
        submitBtn   = findViewById(R.id.submitBtn);


        /*Setting Click Listeners*/
        setClickListeners();
        setHoverEffect();
    }

    /**
     * Here we set all the click listeners for the views and buttons.
     */
    public void setClickListeners(){

        menuIcon.setOnClickListener(this);
        submitBtn.setOnClickListener(this);

    }

    /**
     * Here we set all the click listener hover Effects.
     */
    public void setHoverEffect(){

        Utilites.getSharedInstance().setHoverForButtons(menuIcon);
        Utilites.getSharedInstance().setHoverForButtons(submitBtn);

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.menuIcon) {// to open or close side menu.
            openCloseMenu();
        }
        if (view.getId() == R.id.submitBtn){

            String opwd = oldpwdEt.getText().toString();
            String npwd=newpwdEt.getText().toString();
            String cpwd=cpwdEt.getText().toString();

            if(opwd.equals("")){
                Toast.makeText(currentActivity, "Current Password can't be empty", Toast.LENGTH_SHORT).show();
            }else if(npwd.equals("")){
                Toast.makeText(currentActivity, "New Password can't be empty", Toast.LENGTH_SHORT).show();
            }else if(cpwd.equals("")){
                Toast.makeText(currentActivity, "Confirm Password can't be empty", Toast.LENGTH_SHORT).show();
            }else if(isValid(npwd,cpwd)) {
                if (!opwd.equals(npwd)) {
                    Toast.makeText(currentActivity, "Password changed Successfully", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(currentActivity, "Old and New password can't be same", Toast.LENGTH_SHORT).show();
                }
            }else {
                Toast.makeText(currentActivity, "Passwords Not Matching", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(getApplicationContext(),LudoMainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
        finish();
    }


    public boolean isValid(String pwd, String cpwd){
        if(!pwd.equals(cpwd)){
            return false;
        }else {
            return true;
        }
    }
}

