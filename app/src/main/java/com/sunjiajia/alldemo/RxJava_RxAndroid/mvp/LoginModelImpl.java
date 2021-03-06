package com.sunjiajia.alldemo.RxJava_RxAndroid.mvp;

import android.os.Handler;
import android.text.TextUtils;

/**
 * Created by mk on 2017/6/16.
 * Class note: 延时模拟登陆（2s）,如果名字或者密码为空则登陆失败，否则登陆成功
 */

public class LoginModelImpl implements LoginModel {

    @Override
    public void login(final String username, final String password, final OnloginFinishedListener listener) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
              boolean error = false;
                if (TextUtils.isEmpty(username)){
                    listener.onUsernameError();//model层里面回调listener
                    error = true;
                }
                if (TextUtils.isEmpty(password)){
                    listener.onPasswordError();
                    error = true;
                }
                if (!error){
                    listener.onSuccess();
                }

            }
        },2000);

    }
}
