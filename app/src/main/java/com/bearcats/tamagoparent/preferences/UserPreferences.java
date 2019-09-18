package com.bearcats.tamagoparent.preferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class UserPreferences {

    static final String KEY_USERID ="user_id";
    static final String KEY_USERNAME = "user_name";
    static final String KEY_USER_TOKEN = "user_token";
    static final String KEY_USER_LOGGED_IN = "user_logged_in";
    static final String KEY_USER_TEL = "user_tel";

    private static SharedPreferences getSharedPreference(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static void setUserId(Context context, int username){
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.putInt(KEY_USERID, username);
        editor.apply();
    }

    public static int getUserId(Context context){
        return getSharedPreference(context).getInt(KEY_USERID, 0);
    }

    public static void setUsername(Context context, String userid){
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.putString(KEY_USERNAME, userid);
        editor.apply();
    }

    public static String getUsername(Context context){
        return getSharedPreference(context).getString(KEY_USERNAME,"");
    }

    public static void setUserTel(Context context, String userTel){
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.putString(KEY_USER_TEL, userTel);
        editor.apply();
    }

    public static String getUserTel(Context context){
        return getSharedPreference(context).getString(KEY_USER_TEL,"");
    }

    public static void setUserToken(Context context, String usertoken){
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.putString(KEY_USER_TOKEN, usertoken);
        editor.apply();
    }

    public static String getUserToken(Context context){
        return getSharedPreference(context).getString(KEY_USER_TOKEN,"");
    }

    public static void setUserLoggedIn(Context context, Boolean loggedin){
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.putBoolean(KEY_USER_LOGGED_IN, loggedin);
        editor.apply();
    }

    public static Boolean getUserLoggedIn(Context context){
        return getSharedPreference(context).getBoolean(KEY_USER_LOGGED_IN,false);
    }

    public static void clearLoggedInUser (Context context){
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.remove(KEY_USERID);
        editor.remove(KEY_USERNAME);
        editor.remove(KEY_USER_TOKEN);
        editor.remove(KEY_USER_LOGGED_IN);
        editor.apply();
    }
}