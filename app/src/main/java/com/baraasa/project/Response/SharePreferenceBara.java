package com.baraasa.project.Response;

import android.content.Context;
import android.content.SharedPreferences;

public class SharePreferenceBara {

    Context context;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    private static final String TOKEN = "token";
    private static final String USER = "user";


    public SharePreferenceBara(Context context) {
        this.context = context;
    }



}
