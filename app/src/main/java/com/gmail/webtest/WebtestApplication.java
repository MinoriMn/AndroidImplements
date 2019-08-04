package com.gmail.webtest;

import android.app.Application;

import io.realm.Realm;

public class WebtestApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Realm.init(getApplicationContext());
    }
}
