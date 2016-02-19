package com.mobilekillers.chat;

import android.os.Build;
import android.util.Log;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.junit.Test;

import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class WebsocketUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        URI uri;
        try {
            uri = new URI("ws://kraken-test-socksjs.herokuapp.com/echo/websocket");
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return;
        }
        WebSocketClient mWebSocketClient = null;
        final WebSocketClient finalMWebSocketClient = mWebSocketClient;
        mWebSocketClient = new WebSocketClient(uri) {
            @Override
            public void onOpen(ServerHandshake serverHandshake) {
                Log.i("Websocket", "Opened");
                finalMWebSocketClient.send("Hello from " + Build.MANUFACTURER + " " + Build.MODEL);
            }

            @Override
            public void onMessage(String s) {

            }

            @Override
            public void onClose(int i, String s, boolean b) {
                Log.i("Websocket", "Closed " + s);
            }

            @Override
            public void onError(Exception e) {
                Log.i("Websocket", "Error " + e.getMessage());
            }
        };
        mWebSocketClient.connectBlocking();
        assertEquals(4, 2 + 2);
    }
}