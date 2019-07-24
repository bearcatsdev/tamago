package com.bearcats.tamagoparent.conn;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

public class NetworkManager {
    private static NetworkManager instance = null;
    private static final String TAG = "NetworkManager";

    private static final String SERVER_URL = "https://tamago.bancet.cf";

    public RequestQueue requestQueue;

    public NetworkManager(Context context) {
        requestQueue = Volley.newRequestQueue(context.getApplicationContext());
    }

    public static synchronized NetworkManager getInstance(Context context) {
        if (null == instance)
            instance = new NetworkManager(context);
        return instance;
    }

    public static synchronized NetworkManager getInstance() {
        if (null == instance)
        {
            throw new IllegalStateException(NetworkManager.class.getSimpleName() +
                    " is not initialized, call getInstance(...) first");
        }
        return instance;
    }

    public interface postCallback {
        void onSuccess();
        void onError(String err);
    }

    public void userLogin(String userTel, postCallback callback) {
        String url =  SERVER_URL + "/api/user/login";
        Map<String,String> params = new HashMap<>();
        params.put("user_tel", userTel);
        JSONObject jsonBody = new JSONObject(params);

        Log.d(TAG, jsonBody.toString());

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                url, jsonBody,
                response -> {
                    Log.d("wkwkwkw", response.toString());
                    try {
                        JSONObject jsonObject = new JSONObject(response.toString());
                        Integer networkStatus = jsonObject.getInt("status");
                        String otpStatus = jsonObject.getString("response");

                        if (networkStatus == 404) {
                            // user not found
                            callback.onError("usernotfound");

                        } else if (networkStatus == 200) {
                            if (otpStatus.equals("OTP sent successfully")) {
                                // otp success
                                callback.onSuccess();

                            } else if (otpStatus.equals("OTP send failed")) {
                                // otp failed
                                callback.onError("otperror");
                            }
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.e(TAG, e.getLocalizedMessage());
                    }
                },
                error -> {
                    JSONObject jsonObject;
                    String errorReason = null;
                    try {
                        String responseBody = new String(error.networkResponse.data, "utf-8");
                        jsonObject = new JSONObject( responseBody );
                        Log.d(TAG, jsonObject.getString("error"));
                        errorReason = jsonObject.getString("error");
                    } catch (Exception e)  {
                        Log.e(TAG, e.getLocalizedMessage());
                    }

                    if (errorReason != null) {
                        callback.onError(errorReason);
                    } else {
                        callback.onError(error.getLocalizedMessage());
                    }
                });

        requestQueue.add(jsonObjReq);
    }



    public String getServerUrl() { return SERVER_URL; }
}
