package com.mobilekillers.chat.application;

import com.mobilekillers.chat.dagger.scopes.ApplicationScope;
import com.mobilekillers.chat.network.NetworkComponent;
import com.mobilekillers.chat.presenters.ChatActivityPresenter;

import dagger.Component;

/**
 * Created by sergey on 18.02.16.
 */

@ApplicationScope
@Component(
        dependencies = {
                NetworkComponent.class
        },
        modules = {
                AppModule.class
        }
)

public interface AppComponent {
        void inject(TheApplication application);
        void inject(ChatActivityPresenter presenter);
}
