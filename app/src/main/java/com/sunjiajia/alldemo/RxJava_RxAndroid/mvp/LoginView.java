package com.sunjiajia.alldemo.RxJava_RxAndroid.mvp;

/**
 * Created by mk on 2017/6/16.
 * class note:登陆View的接口，实现也就是登陆的activity
 */

public interface LoginView {
    void showProgress();

    void hideProgress();

    void setUsernameError();

    void setPasswordError();

    void navigateToHome();
}
