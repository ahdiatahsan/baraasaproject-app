package com.baraasa.project.Response;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.prefs.Preferences;

public class LocalStorage {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Context context;

    public static final String TOKEN_BARA = "token";
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String ROLE = "roles";
    public static final String FOTO = "foto";
    public static final String GENDER = "gender";
    public static final String BIRDTHPLEACE = "tempatlahir";
    public static final String DATE_OF_BIRTH = "tgllhir";
    public static final String ADDRESS = "alamat";
    public static final String PHONE_NUMBER = "notpln";
    public static final String EMAIL = "email";

    public LocalStorage(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences("STORAGE_LOGIN_API", Context.MODE_PRIVATE);
    }

    public void save(String key, String value) {
        editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public String getStringValue(String key) {
        return sharedPreferences.getString(key, null);
    }

    public void clearSharePreference() {
        editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }

    public void removeValue(String key) {
        editor = sharedPreferences.edit();
        editor.remove(key);
        editor.apply();
    }

}
