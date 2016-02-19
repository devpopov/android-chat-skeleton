package com.mobilekillers.chat.application;

import android.app.Application;

import com.mobilekillers.chat.dagger.scopes.ApplicationScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by sergey on 18.02.16.
 */
@Module
public class AppModule {
    private Application mApplication;

    public AppModule(Application application) {
        mApplication = application;
    }

    @Provides
    @ApplicationScope
    public Application provideApplication() {
        return mApplication;
    }
}
