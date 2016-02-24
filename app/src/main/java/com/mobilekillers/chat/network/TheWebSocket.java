package com.mobilekillers.chat.network;

import android.content.Context;

import com.mobilekillers.chat.R;
import com.mobilekillers.chat.events.app.ErrorEvent;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;

import de.greenrobot.event.EventBus;

/**
 * Created by sergey on 18.02.16.
 */
public class TheWebSocket {
    private WebSocketClient mWebSocket = null;
    private Context mContext = null;

    private String mUri = null;

    private boolean mIsOpen = false;

    private SocketCallback mCallback = new SocketCallback() {
        @Override
        public void onOpen(ServerHandshake handshakedata) {

        }

        @Override
        public void onMessage(String message) {

        }

        @Override
        public void onClose(int code, String reason, boolean remote) {

        }

        @Override
        public void onError(Exception ex) {

        }
    };

    public void recreate() {
        try {
            mWebSocket = new WebSocketClient(new URI(mUri)) {
                @Override
                public void onOpen(ServerHandshake handshakedata) {
                    mIsOpen = true;
                    if(mCallback != null) {
                        mCallback.onOpen(handshakedata);
                    }
                }

                @Override
                public void onMessage(String message) {
                    if(mCallback != null) {
                        mCallback.onMessage(message);
                    }
                }

                @Override
                public void onClose(int code, String reason, boolean remote) {
                    if(mCallback != null) {
                        mCallback.onClose(code, reason, remote);
                    }
                }

                @Override
                public void onError(Exception ex) {
                    mIsOpen = false;
                    if(mCallback != null) {
                        mCallback.onError(ex);
                    }
                    else
                        EventBus.getDefault().post(new ErrorEvent(mContext.getString(R.string.connection_error)));
                }
            };
        } catch (URISyntaxException e) {
            e.printStackTrace();
            EventBus.getDefault().post(new ErrorEvent(mContext.getString(R.string.connection_error)));
        }
    }

    public void setCallback(SocketCallback callback) {
        mCallback = callback;
    }

    public void connect() {
        mWebSocket.connect();
    }

    public void send(String message) {
        mWebSocket.send(message);
    }

    public void close() {
        if (mIsOpen) {
            mWebSocket.close();
            mIsOpen = false;
        }
    }

    public boolean isOpen() {
        return mIsOpen;
    }

    public TheWebSocket(Context context, String uri) {
        mContext = context;

        mUri = uri;

        recreate();
    }
}
