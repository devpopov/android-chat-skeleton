package com.mobilekillers.chat.network;

import com.mobilekillers.chat.application.SingletonComponent;

import dagger.Component;

/**
 * Created by sergey on 18.02.16.
 */
@Component(
        modules = {
                NetworkModule.class
        }
)
public interface NetworkComponent extends SingletonComponent {
        TheWebSocket socket();
}
