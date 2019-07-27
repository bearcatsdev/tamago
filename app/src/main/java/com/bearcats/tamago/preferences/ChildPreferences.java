package com.bearcats.tamago.preferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class ChildPreferences {
    static final String KEY_CHILDID ="user_id";
    static final String KEY_CHILDNAME = "user_name";
    static final String KEY_CHILD_TOKEN = "user_token";
    static final String KEY_CHILD_LOGGED_IN = "user_logged_in";
    static final String KEY_CHILD_WALLET = "user_wallet";
    static final String KEY_CHILD_SAVING = "user_wallet";
    static final String KEY_CHILD_EGG = "user_wallet";

    private static SharedPreferences getSharedPreference(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static void setChildId(Context context, int username){
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.putInt(KEY_CHILDID, username);
        editor.apply();
    }

    public static int getChildId(Context context){
        return getSharedPreference(context).getInt(KEY_CHILDID, 0);
    }

    public static void setChildname(Context context, String userid){
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.putString(KEY_CHILDNAME, userid);
        editor.apply();
    }

    public static String getChildname(Context context){
        return getSharedPreference(context).getString(KEY_CHILDNAME,"");
    }


    public static void setChildToken(Context context, String usertoken){
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.putString(KEY_CHILD_TOKEN, usertoken);
        editor.apply();
    }

    public static String getChildToken(Context context){
        return getSharedPreference(context).getString(KEY_CHILD_TOKEN,"");
    }

    public static void setChildLoggedIn(Context context, Boolean loggedin){
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.putBoolean(KEY_CHILD_LOGGED_IN, loggedin);
        editor.apply();
    }

    public static Boolean getChildLoggedIn(Context context){
        return getSharedPreference(context).getBoolean(KEY_CHILD_LOGGED_IN,false);
    }

    public static void setChildWallet(Context context, int wallet){
        SharedPreferences.Editor editor= getSharedPreference(context).edit();
        editor.putInt(KEY_CHILD_WALLET,wallet);
        editor.apply();
    }

    public static int getChildWallet(Context context){
        return getSharedPreference(context).getInt(KEY_CHILD_WALLET,0);
    }

    public static void setChildSaving(Context context, int saving){
        SharedPreferences.Editor editor= getSharedPreference(context).edit();
        editor.putInt(KEY_CHILD_SAVING,saving);
        editor.apply();
    }

    public static int getChildSaving(Context context){
        return getSharedPreference(context).getInt(KEY_CHILD_SAVING,0);
    }

    public static void setChildEgg(Context context, int egg){
        SharedPreferences.Editor editor= getSharedPreference(context).edit();
        editor.putInt(KEY_CHILD_EGG,egg);
        editor.apply();
    }

    public static int getChildEgg(Context context){
        return getSharedPreference(context).getInt(KEY_CHILD_EGG,0);
    }



    public static void clearLoggedInUser (Context context){
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.remove(KEY_CHILDID);
        editor.remove(KEY_CHILDNAME);
        editor.remove(KEY_CHILD_TOKEN);
        editor.remove(KEY_CHILD_LOGGED_IN);
        editor.remove(KEY_CHILD_WALLET);
        editor.remove(KEY_CHILD_SAVING);
        editor.remove(KEY_CHILD_EGG);
        editor.apply();
    }
}
