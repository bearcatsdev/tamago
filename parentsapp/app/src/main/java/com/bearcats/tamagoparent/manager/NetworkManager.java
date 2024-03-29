package com.bearcats.tamagoparent.manager;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class NetworkManager {
    private static NetworkManager instance = null;
    private static final String TAG = "NetworkManager";
    private static final String SERVER_URL = "https://tamago.bancet.cf";

    private RequestQueue requestQueue;

    public NetworkManager(Context context) {
        requestQueue = Volley.newRequestQueue(context.getApplicationContext());
    }

    public static synchronized NetworkManager getInstance(Context context) {
        if (null == instance)
            instance = new NetworkManager(context);
        return instance;
    }
    public static synchronized NetworkManager getInstance() {
        if (null == instance) {
            throw new IllegalStateException(NetworkManager.class.getSimpleName() +
                    " is not initialized, call getInstance(...) first");
        }
        return instance;
    }

    public interface postObjectCallback {
        void onResponse(Boolean success, JSONObject response) throws JSONException;
    }
    public interface postArrayCallback {
        void onResponse(Boolean success, JSONArray response) throws JSONException;
    }

    public void userLogin(String userTel, postObjectCallback callback) {
        String url =  SERVER_URL + "/api/user/login";
        Map<String,String> params = new HashMap<>();
        params.put("user_tel", userTel);
        JSONObject jsonBody = new JSONObject(params);

        Log.d(TAG, jsonBody.toString());

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                url, jsonBody,
                response -> {
                    Log.d(TAG, response.toString());
                    try {
                        JSONObject jsonObject = new JSONObject(response.toString());
                        Integer networkStatus = jsonObject.getInt("status");

                        if (networkStatus == 200) {
                            callback.onResponse(true, jsonObject);
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.e(TAG, e.getLocalizedMessage());
                    }
                },
                error -> {
                    try {
                        String responseBody = new String(error.networkResponse.data, StandardCharsets.UTF_8);
                        JSONObject jsonObject = new JSONObject(responseBody);
                        String errorReason = jsonObject.getString("reason");
                        Log.e(TAG, errorReason);

                        if (errorReason != null) {
                            JSONObject errorObject = new JSONObject();
                            errorObject.put("reason", errorReason);
                            callback.onResponse(false, errorObject);

                        } else {
                            JSONObject errorObject = new JSONObject();
                            errorObject.put("reason", error.getLocalizedMessage());
                            callback.onResponse(false, errorObject);
                        }

                    } catch (Exception e)  {
                        Log.e(TAG, e.getLocalizedMessage());
                    }
                });

        requestQueue.add(jsonObjReq);
    }

    public void registerUser(String userName, String userTel, postObjectCallback callback) {
        String url =  SERVER_URL + "/api/user/newUser";
        Map<String,String> params = new HashMap<>();
        params.put("user_tel", userTel);
        params.put("user_name", userName);
        JSONObject jsonBody = new JSONObject(params);

        Log.d(TAG, jsonBody.toString());

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                url, jsonBody,
                response -> {
                    Log.d(TAG, response.toString());
                    try {
                        JSONObject jsonObject = new JSONObject(response.toString());
                        Integer networkStatus = jsonObject.getInt("status");

                        if (networkStatus == 200) {
                            callback.onResponse(true, jsonObject);
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.e(TAG, e.getLocalizedMessage());
                    }
                },
                error -> {
                    try {
                        String responseBody = new String(error.networkResponse.data, StandardCharsets.UTF_8);
                        JSONObject jsonObject = new JSONObject(responseBody);
                        String errorReason = jsonObject.getString("reason");
                        Log.e(TAG, errorReason);

                        if (errorReason != null) {
                            JSONObject errorObject = new JSONObject();
                            errorObject.put("reason", errorReason);
                            callback.onResponse(false, errorObject);

                        } else {
                            JSONObject errorObject = new JSONObject();
                            errorObject.put("reason", error.getLocalizedMessage());
                            callback.onResponse(false, errorObject);
                        }

                    } catch (Exception e)  {
                        Log.e(TAG, e.getLocalizedMessage());
                    }
                });

        requestQueue.add(jsonObjReq);
    }

    public void verifyOtp(String userTel, String otp, postObjectCallback callback) {
        String url =  SERVER_URL + "/api/user/verifyOtp";
        Map<String,String> params = new HashMap<>();
        params.put("user_tel", userTel);
        params.put("otp", otp);
        JSONObject jsonBody = new JSONObject(params);

        Log.d(TAG, jsonBody.toString());

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                url, jsonBody,
                response -> {
                    Log.d(TAG, response.toString());
                    try {
                        JSONObject jsonObject = new JSONObject(response.toString());
                        Integer networkStatus = jsonObject.getInt("status");

                        if (networkStatus == 200) {
                            callback.onResponse(true, jsonObject.getJSONObject("response"));
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.e(TAG, e.getLocalizedMessage());
                    }
                },
                error -> {
                    try {
                        String responseBody = new String(error.networkResponse.data, StandardCharsets.UTF_8);
                        JSONObject jsonObject = new JSONObject(responseBody);
                        String errorReason = jsonObject.getString("reason");
                        Log.e(TAG, errorReason);

                        if (errorReason != null) {
                            JSONObject errorObject = new JSONObject();
                            errorObject.put("reason", errorReason);
                            callback.onResponse(false, errorObject);

                        } else {
                            JSONObject errorObject = new JSONObject();
                            errorObject.put("reason", error.getLocalizedMessage());
                            callback.onResponse(false, errorObject);
                        }

                    } catch (Exception e)  {
                        Log.e(TAG, e.getLocalizedMessage());
                    }
                });

        requestQueue.add(jsonObjReq);
    }

    public void getChildrenList(int parentId, postArrayCallback callback) {
        String url =  SERVER_URL + "/api/user/getChildrenList";
        Map<String,Integer> params = new HashMap<>();
        params.put("parent_id", parentId);
        JSONObject jsonBody = new JSONObject(params);

        Log.d(TAG, jsonBody.toString());

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                url, jsonBody,
                response -> {
                    Log.d(TAG, response.toString());
                    try {
                        JSONObject jsonObject = new JSONObject(response.toString());
                        Integer networkStatus = jsonObject.getInt("status");

                        if (networkStatus == 200) {
                            callback.onResponse(true, jsonObject.getJSONArray("response"));
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.e(TAG, e.getLocalizedMessage());
                    }
                },
                error -> {
                    try {
                        String responseBody = new String(error.networkResponse.data, StandardCharsets.UTF_8);
                        JSONObject jsonObject = new JSONObject(responseBody);
                        String errorReason = jsonObject.getString("reason");
                        Log.e(TAG, errorReason);

                        if (errorReason != null) {
                            JSONArray errorArray = new JSONArray();
                            JSONObject errorObject = new JSONObject();
                            errorObject.put("reason", errorReason);
                            errorArray.put(0, jsonObject);
                            callback.onResponse(false, errorArray);

                        } else {
                            JSONArray errorArray = new JSONArray();
                            JSONObject errorObject = new JSONObject();
                            errorObject.put("reason", error.getLocalizedMessage());
                            errorArray.put(0, jsonObject);
                            callback.onResponse(false, errorArray);
                        }

                    } catch (Exception e)  {
                        Log.e(TAG, e.getLocalizedMessage());
                    }
                });

        requestQueue.add(jsonObjReq);
    }

    public String getServerUrl() { return SERVER_URL; }
}
