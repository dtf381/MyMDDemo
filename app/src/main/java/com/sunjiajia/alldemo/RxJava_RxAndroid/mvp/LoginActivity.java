package com.sunjiajia.alldemo.RxJava_RxAndroid.mvp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.sunjiajia.androidnewwidgetsdemo.R;

/**
 * Created by mk on 2017/6/16.
 */

public class LoginActivity extends Activity implements LoginView, View.OnClickListener {
    private ProgressBar progressBar;
    private EditText username;
    private EditText password;
    private LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mvp_activity_login);

        progressBar = (ProgressBar) findViewById(R.id.progress);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        findViewById(R.id.button).setOnClickListener(this);

        presenter = new LoginPresenterImpl(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void setUsernameError() {
        username.setError("userName is Error");
    }

    @Override
    public void setPasswordError() {
        password.setError("password is Error");
    }

    @Override
    public void navigateToHome() {
        Toast.makeText(this,"login suceess",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        presenter.validateCredentials(username.getText().toString(),password.getText().toString());
    }
}
