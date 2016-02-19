package com.mobilekillers.chat.network;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

/**
 * Created by sergey on 18.02.16.
 */
@Module
public class NetworkModule {
    private TheWebSocket mWebSocket;

    public NetworkModule(Context context) {
        mWebSocket = new TheWebSocket(context);
    }

    @Provides
    public TheWebSocket provideWebSocket() {
        return mWebSocket;
    }
}
