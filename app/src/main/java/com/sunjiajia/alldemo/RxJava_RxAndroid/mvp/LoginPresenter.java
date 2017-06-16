package com.sunjiajia.alldemo.RxJava_RxAndroid.mvp;

/**
 * Created by mk on 2017/6/16.
 * Class Note：登陆的Presenter的接口，实现类为LoginPresenterImpl,完成登陆的验证，以及销毁当前View
 */

public interface LoginPresenter {
    void validateCredentials(String username, String password);

    void onDestroy();
}
