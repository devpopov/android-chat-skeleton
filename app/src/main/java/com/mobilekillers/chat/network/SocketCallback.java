package com.mobilekillers.chat.network;

import org.java_websocket.handshake.ServerHandshake;

/**
 * Created by sergey on 18.02.16.
 */
public interface SocketCallback {
    void onOpen(ServerHandshake handshakedata);
    void onMessage(String message);
    void onClose(int code, String reason, boolean remote);
    void onError(Exception ex);

}
