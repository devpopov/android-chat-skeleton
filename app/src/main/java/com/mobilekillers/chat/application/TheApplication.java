package com.mobilekillers.chat.application;

import android.app.Application;
import android.content.Context;

import com.mobilekillers.chat.network.DaggerNetworkComponent;
import com.mobilekillers.chat.network.NetworkComponent;
import com.mobilekillers.chat.network.NetworkModule;

/**
 * Created by sergey on 18.02.16.
 */
public class TheApplication extends Application {
    private AppComponent mAppComponent;

    public static TheApplication get(Context context) {
        return (TheApplication) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initComponent();
    }

    public void initComponent() {
        NetworkComponent networkComponent = DaggerNetworkComponent.builder()
                .networkModule(new NetworkModule(getApplicationContext()))
                .build();

        mAppComponent = DaggerAppComponent.builder()
                .networkComponent(networkComponent)
                .appModule(new AppModule(this))
                .build();
        mAppComponent.inject(this);
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }
}
