package com.smart.ludoclassic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.BaseJsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.smart.ludoclassic.API.RestClientClass;
import com.smart.ludoclassic.modals.UserModal;

import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;


public class SignInActivity extends AppCompatActivity {
    EditText userEt,pwdEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        userEt = findViewById(R.id.userEt);
        pwdEt = findViewById(R.id.pwdEt);

        findViewById(R.id.signupBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),SignUpActivity.class);
                startActivity(i);

            }
        });
        findViewById(R.id.signinBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(userEt.getText().toString().equals("")){
                    Toast.makeText(SignInActivity.this, "Please enter valid Username", Toast.LENGTH_SHORT).show();
                }else if(pwdEt.getText().toString().equals("")){
                    Toast.makeText(SignInActivity.this, "Please enter valid Password", Toast.LENGTH_SHORT).show();
                }else {
                    makeLoginApiCall();
                }
            }
        });
    }

    public void makeLoginApiCall(){
        AsyncHttpClient client = new AsyncHttpClient();
        client.addHeader("Authorization", "b8416f2680eb194d61b33f9909f94b9d");
        client.addHeader("Content-Type", "application/json");
        JSONObject jsonParams = new JSONObject();
        StringEntity entity = null;

        try {

            jsonParams.put("username", userEt.getText().toString());
            jsonParams.put("password",pwdEt.getText().toString());
            entity = new StringEntity(jsonParams.toString());
            Log.e("Request Body",""+jsonParams.toString());
            client.post(SignInActivity.this,getResources().getString(R.string.baseURL)+getResources().getString(R.string.login) , entity, "application/json", new BaseJsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, String rawJsonResponse, Object response) {
                    Log.e("Submit Response ",""+rawJsonResponse);
                    ObjectMapper mapper=new ObjectMapper();
                    try {
                        UserModal responseSuccessModal = mapper.readValue(rawJsonResponse, UserModal.class);
                        SharedPreferences pref = getApplicationContext().getSharedPreferences("Userdetails", 0); // 0 - for private mode
                        SharedPreferences.Editor editor = pref.edit();
                        editor.putString("username",responseSuccessModal.getUserDetails().getUsername());
                        editor.putString("email", responseSuccessModal.getUserDetails().getEmail());
                        editor.putString("mobile",responseSuccessModal.getUserDetails().getMobile());
                        editor.putString("userId",responseSuccessModal.getUserDetails().getUserId());
                        editor.putBoolean("islogin",true);
                        editor.apply();
                        if(responseSuccessModal.getStatus().equals("0")){
                            Toast.makeText(SignInActivity.this, responseSuccessModal.getMessage(), Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(SignInActivity.this, ""+responseSuccessModal.getMessage(), Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(getApplicationContext(),LudoMainActivity.class);
                            startActivity(i);
                            finish();
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, Throwable throwable, String rawJsonData, Object errorResponse) {
                    Log.e("onFailure "," "+rawJsonData +" "+throwable.getLocalizedMessage());

                }

                @Override
                protected Object parseResponse(String rawJsonData, boolean isFailure) throws Throwable {
                    return null;
                }
            });

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

