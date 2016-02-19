package com.mobilekillers.chat.presenters;

import android.os.Bundle;

import com.mobilekillers.chat.R;
import com.mobilekillers.chat.application.TheApplication;
import com.mobilekillers.chat.events.app.ErrorEvent;
import com.mobilekillers.chat.models.User;
import com.mobilekillers.chat.network.SocketCallback;
import com.mobilekillers.chat.network.TheWebSocket;
import com.mobilekillers.chat.views.ChatActivity;

import org.java_websocket.handshake.ServerHandshake;

import javax.inject.Inject;

import de.greenrobot.event.EventBus;
import nucleus.presenter.RxPresenter;

/**
 * Created by sergey on 19.02.16.
 */
public class ChatActivityPresenter extends RxPresenter<ChatActivity> {
    private User mUser;

    @Inject
    TheWebSocket webSocket;

    @Override
    protected void onCreate(Bundle savedState) {
        super.onCreate(savedState);
    }

    @Override
    protected void onTakeView(ChatActivity view) {
        super.onTakeView(view);

        TheApplication.get(getView()).getAppComponent().inject(this);

        EventBus.getDefault().register(this);

        webSocket.recreate();

        mUser = (User)getView().getIntent().getSerializableExtra("user");

        webSocket.setCallback(new SocketCallback() {
            @Override
            public void onOpen(ServerHandshake handshakedata) {
                webSocket.send(mUser.getNickname() + " joined chat");
            }

            @Override
            public void onMessage(final String message) {
                if (getView() != null) {
                    getView().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            getView().appendMessage(message);
                        }
                    });
                }
            }

            @Override
            public void onClose(int code, String reason, boolean remote) {
            }

            @Override
            public void onError(Exception ex) {
                if (getView() != null) {
                    getView().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            getView().showMessage(R.string.connection_error);
                        }
                    });
                }
            }
        });

        webSocket.connect();
    }

    @Override
    protected void onDropView() {
        super.onDropView();
        if(webSocket.isOpen())
            webSocket.send(mUser.getNickname() + " leave chat");
        webSocket.close();
    }

    public void onEvent(ErrorEvent event) {
        if (getView() != null) {
            getView().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    getView().showMessage(R.string.connection_error);
                }
            });
        }
    }

    public void onSend(String message) {
        webSocket.send(mUser.getNickname() + ": " + message);
    }
}
