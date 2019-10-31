package com.felipe.relatorioveiculo;

import android.app.Application;

import io.realm.Realm;

public class MeuApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
//        Realm.deleteRealm(Realm.getDefaultConfiguration());
    }
}