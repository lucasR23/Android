package com.longynuss.helper;


import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;

public class MyPreferences {
    private Context context;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private final String FILE_NAME = "whatsappclone.file";

    private final String KEY_NAME="name";
    private final String KEY_PHONE="phone";
    private final String KEY_TOKEN="token";

    public MyPreferences(Context context) {
        this.context = context;
        preferences = this.context.getSharedPreferences(FILE_NAME,Context.MODE_PRIVATE);
        editor = preferences.edit();
    }

    public void saveUserPreferences(String name, String phone, String token){
        editor.putString(KEY_NAME,name);
        editor.putString(KEY_PHONE,phone);
        editor.putString(KEY_TOKEN,token);
        editor.commit();
    }

    public HashMap<String,String> getUserData(){
        HashMap<String,String> userData = new HashMap<>();
        userData.put(KEY_NAME, preferences.getString(KEY_NAME,null));
        userData.put(KEY_PHONE, preferences.getString(KEY_PHONE,null));
        userData.put(KEY_TOKEN, preferences.getString(KEY_TOKEN,null));

        return userData;
    }
}
