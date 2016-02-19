package com.mobilekillers.chat.views;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.widget.EditText;
import android.widget.Toast;

import com.mobilekillers.chat.R;
import com.mobilekillers.chat.presenters.AuthActivityPresenter;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import nucleus.factory.RequiresPresenter;
import nucleus.view.NucleusAppCompatActivity;

@RequiresPresenter(AuthActivityPresenter.class)
public class AuthActivity extends NucleusAppCompatActivity<AuthActivityPresenter> {

    @Bind(R.id.nickname)
    EditText nicknameEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.join)
    public void join() {
        getPresenter().onJoin(nicknameEdit.getText().toString());
    }

    public void showMessage(int id) {
        Toast toast = Toast.makeText(getApplicationContext(), getResources().getString(id), Toast.LENGTH_SHORT);
        toast.show();
    }
}
