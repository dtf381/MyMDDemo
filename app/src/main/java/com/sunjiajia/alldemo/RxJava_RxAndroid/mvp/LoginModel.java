package com.sunjiajia.alldemo.RxJava_RxAndroid.mvp;

/**
 * 2.1 model层描述和具体代码
 * 提供我们想要展示在view层的数据和具体登陆业务逻辑处理的实现，
 *
 * Created by mk on 2017/6/16.
 * Class Note: 模拟登陆的操作的接口，实现类为LoginModelImpl.相当于MVP模式中的Mode层。
 */
public interface LoginModel {
    void login(String username,String password,OnloginFinishedListener listener);

}
