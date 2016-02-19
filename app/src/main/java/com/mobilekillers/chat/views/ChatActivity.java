package com.mobilekillers.chat.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mobilekillers.chat.R;
import com.mobilekillers.chat.presenters.ChatActivityPresenter;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import nucleus.factory.RequiresPresenter;
import nucleus.view.NucleusAppCompatActivity;

@RequiresPresenter(ChatActivityPresenter.class)
public class ChatActivity extends NucleusAppCompatActivity<ChatActivityPresenter> {

    @Bind(R.id.chat)
    TextView chatView;

    @Bind(R.id.message)
    EditText messageEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.send)
    public void send() {
        getPresenter().onSend(messageEdit.getText().toString());
    }

    public void appendMessage(String message) {
        if(chatView.length() != 0)
            chatView.append("\n");
        chatView.append(message);
    }

    public void showMessage(int id) {
        Toast toast = Toast.makeText(getApplicationContext(), getResources().getString(id), Toast.LENGTH_SHORT);
        toast.show();
    }
}
