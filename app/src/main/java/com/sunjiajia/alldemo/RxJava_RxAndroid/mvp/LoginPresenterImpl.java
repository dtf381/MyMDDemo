package com.sunjiajia.alldemo.RxJava_RxAndroid.mvp;

import android.support.v4.util.LruCache;

/**
 * Created by mk on 2017/6/16.
 * Class note:
 * 1.完成presenter的实现。这里面主要是Model层和View层的交互和操作。
 * 2.presenter里面还有个OnLoginFinishedListener,其在presenter层实现，给Model层回调，更改View层的状态。
 *   确保Model层不直接操作View层。如果没有这一接口在LoginPresenterImpl实现的话，LoginPresenterImpl只有View和
 *   Model的引用那么Model怎样把结果告诉View呢？
 */

public class LoginPresenterImpl implements LoginPresenter ,OnloginFinishedListener{
    private LoginView loginView;
    private LoginModel loginModel;

    public LoginPresenterImpl(LoginView loginView) {
        this.loginView = loginView;
        this.loginModel = new LoginModelImpl();
    }

    @Override
    public void validateCredentials(String username, String password) {
        if (loginView != null){
            loginView.showProgress();
        }
        loginModel.login(username,password,this);
    }

    @Override
    public void onDestroy() {
        loginView = null;
    }

    @Override
    public void onUsernameError() {
        if (loginView != null){
            loginView.setUsernameError();
            loginView.hideProgress();
        }
    }

    @Override
    public void onPasswordError() {
        if (loginView != null){
            loginView.setPasswordError();
            loginView.hideProgress();
        }

    }

    @Override
    public void onSuccess() {
        if (loginView != null){
            loginView.navigateToHome();
        }

    }
}
