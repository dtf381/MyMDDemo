package com.sunjiajia.alldemo.RxJava_RxAndroid;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by mk on 2017/3/1.
 * 　(1)Observable (2)Subscriber 即：被观察者(Observable)和观察者(Subscriber)，其实我觉得叫发布者和订阅者更好理解一些，但大家都叫被观察者和观察者。
 */

public class RxJavaTest {

    //创建一个发布者  (有问题)
    Observable observable = Observable.create(new ObservableOnSubscribe() {
        @Override
        public void subscribe(ObservableEmitter observableEmitter) throws Exception {

        }
    });


    //创建一个观察者
    Subscriber<Integer> subscriber = new Subscriber<Integer>() {
        @Override
        public void onSubscribe(Subscription s) {

        }

        @Override
        public void onNext(Integer integer) {

        }

        @Override
        public void onError(Throwable t) {

        }

        @Override
        public void onComplete() {

        }
    };
    private void initDatas(){
        observable.subscribe((Observer) subscriber);
    }

    private void observable(){
    }

}
