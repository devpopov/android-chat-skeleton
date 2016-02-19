package com.mobilekillers.chat.models;

import java.util.Map;

/**
 * Created by sergey on 18.02.16.
 */
public class User extends DataModel {
    protected Object nickname = null;

    public User() {

    }

    public User(Map<String, Object> data) {
        super(data);
    }

    public Object getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
