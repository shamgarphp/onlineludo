package com.smart.ludoclassic.API;

import android.app.Activity;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.smart.ludoclassic.R;

public class RestClientClass {

    private String BASE_URL;

    private AsyncHttpClient client = new AsyncHttpClient ();


    public RestClientClass(Activity activity) {
        BASE_URL = activity.getResources().getString(R.string.baseURL);
    }


    public  void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        Log.e("API URL ",""+getAbsoluteUrl(url));
        Log.e("API URL ","Params "+params.toString());
            client.get(getAbsoluteUrl(url), params, responseHandler);
    }

    public  void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        Log.e("API URL ",""+getAbsoluteUrl(url));
        Log.e("API URL ","Params "+params.toString());

            client.post(getAbsoluteUrl(url), params, responseHandler);
    }

    private String getAbsoluteUrl(String relativeUrl) {
            return BASE_URL + relativeUrl;
    }

}
