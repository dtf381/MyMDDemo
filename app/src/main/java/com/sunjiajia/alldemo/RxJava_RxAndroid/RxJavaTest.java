package com.sunjiajia.alldemo.RxJava_RxAndroid;

import android.util.Log;

import org.reactivestreams.Subscriber;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by mk on 2017/3/1.
 * 　(1)Observable (2)Subscriber 即：被观察者(Observable)和观察者(Subscriber)，其实我觉得叫发布者和订阅者更好理解一些，但大家都叫被观察者和观察者。
 * http://blog.csdn.net/mlq8087/article/details/51891005
 */
//http://blog.csdn.net/wuyinlei/article/details/52004099
public class RxJavaTest {
    private static final String TAG = RxJavaTest.class.getSimpleName().trim();

    public void oneObservable() {
        //观察者模式，这里产生事件，事件产生后发送给接受者，但是一定要记得将事件的产生者和接受者捆绑在一起，否则会出错。
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                e.onNext("hhhh");
                e.onComplete();
                e.onNext("hhhh");

            }
        }).subscribeOn(Schedulers.newThread()) //线程调度器，将发送者运行在子线程
                .observeOn(AndroidSchedulers.mainThread()) // 接受者运行在主线程
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.e(TAG, "onSubscribe: ");
                        Log.e(TAG, "接收在什么线程" + Thread.currentThread().getName());
                    }

                    @Override
                    public void onNext(String value) {
                        Log.e(TAG, "onNext: " + value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: ", e);
                    }

                    @Override
                    public void onComplete() {
                        Log.e(TAG, "onComplete: ");
                    }
                });

    }


    public void initDatas() {
        //创建一个发布者  (有问题)
        Observable observable = Observable.create(new ObservableOnSubscribe() {
            @Override
            public void subscribe(ObservableEmitter observableEmitter) throws Exception {
                observableEmitter.onNext("55555");
                observableEmitter.onComplete();
            }
        }).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread());

       /* //创建一个观察者
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
        };*/
        Observer observer = new Observer() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.e(TAG, "接收在什么线程" + Thread.currentThread().getName());
            }

            @Override
            public void onNext(Object value) {
                Log.e(TAG, "two onNext = " + (String) value);

            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "two onError = " + (String) e.toString());
            }

            @Override
            public void onComplete() {
                Log.e(TAG, "two onComplete");
            }
        };
        observable.subscribe(observer);
    }


    /**
     * from函数
     *  使用在被观察者，返回的对象一般都是数据类型
     *  它接收一个集合作为输入，然后每次输出一个元素给subscriber
     * */
    public static void from(){
        Integer[] items = {1, 2, 3, 4, 5, 6, 7, 8};
        Observable onservable = Observable.fromArray(items);
        onservable.subscribe(new Observer() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.e(TAG, "two onSubscribe = " +d.toString());
            }

            @Override
            public void onNext(Object value) {
                Log.e(TAG, "two onNext = " + String.valueOf(value));
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "from onError = " + e.toString());
            }

            @Override
            public void onComplete() {
                Log.e(TAG, "from onComplete");
            }
        });

    }
    /*
    * *interter函数*
    *  指定某一时刻进行数据发送
     * interval()函数的两个参数：一个指定两次发射的时间间隔，另一个是用到的时间单位
    * /

    /**
     * just函数
     *
     * 假如我们只有3个独立的AppInfo对象并且我们想把他们转化为Observable并填充到RecyclerView的item中：
     * 这里我们有两个数组，然后通过转化为Observable组成一个item
     */

    public static void just() {
       Integer[] items1 = {1, 2, 3, 4};
        Integer[] items2 = {2, 4, 6, 8};


        Observable observable = Observable.just(items1, items2);
        observable.subscribe(new Observer() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Object value) {
                Integer[] integers = (Integer[]) value;
                for (int i = 0; i < integers.length; i++) {
                    Log.e(TAG, "result--->" + integers[i]);
                }
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.e(TAG, "onCompleted");
            }
        });
    }

    /**
     * range函数
     *  指定输出数据的范围
     */
    public static void range() {
        Observable observable = Observable.range(1, 4);
        observable.subscribe(new Observer() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Object value) {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }
}
