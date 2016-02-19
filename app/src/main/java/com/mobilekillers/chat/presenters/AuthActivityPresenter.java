package com.mobilekillers.chat.presenters;

import android.content.Intent;
import android.os.Bundle;

import com.mobilekillers.chat.R;
import com.mobilekillers.chat.application.TheApplication;
import com.mobilekillers.chat.models.User;
import com.mobilekillers.chat.network.SocketCallback;
import com.mobilekillers.chat.network.TheWebSocket;
import com.mobilekillers.chat.views.AuthActivity;
import com.mobilekillers.chat.views.ChatActivity;

import org.java_websocket.handshake.ServerHandshake;

import javax.inject.Inject;

import nucleus.presenter.RxPresenter;

/**
 * Created by sergey on 18.02.16.
 */
public class AuthActivityPresenter extends RxPresenter<AuthActivity> {
    @Override
    protected void onCreate(Bundle savedState) {
        super.onCreate(savedState);
    }

    public void onJoin(final String nickname) {
        if(nickname.length() != 0) {

            User user = new User();
            user.setNickname(nickname);

            Intent intent = new Intent(getView(), ChatActivity.class);
            intent.putExtra("user", user);
            getView().startActivity(intent);
        }
        else {
            getView().showMessage(R.string.nickname_error);
        }
    }
}
