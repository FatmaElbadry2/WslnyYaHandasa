package com.example.user.WaslnyYaHandasa;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by user on 23/04/2018.
 */

public class SharePreManager {
    private static final String SHARED_PREF_NAME="fcmsharedprefdemo";
    private static final String KEY_ACCESS_TOKEN="token";
    private static Context cntxt;
    private static SharePreManager mInstance;
    private SharePreManager(Context context){
        cntxt=context;
    }
    public static synchronized SharePreManager getmInstance(Context context){
        if (mInstance==null){
            mInstance=new SharePreManager(context);
        }
        return mInstance;
    }
    public boolean StoreToken(String token){
        SharedPreferences sharedPreferences =cntxt.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(KEY_ACCESS_TOKEN,token);
        editor.apply();
        return true;
    }
    public String GetToken(){
        SharedPreferences sharedPreferences =cntxt.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_ACCESS_TOKEN,null);

    }

}
