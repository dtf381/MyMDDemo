package com.sunjiajia.alldemo.RxJava_RxAndroid.mvp;

/**
 * Created by mk on 2017/6/16.
 * Class note: 登陆事件监听
 */

public interface OnloginFinishedListener {
    void onUsernameError();
    void onPasswordError();
    void onSuccess();
}
