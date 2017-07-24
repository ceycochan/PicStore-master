package com.nshane.picstore.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.nshane.picstore.R;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by lbl on 2017-7-3.
 */

public class RXJavaActivity extends AppCompatActivity {

    private String TAG = "cg";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava);
        basicRXTest();

        disposalTest();

        consumerTest();//lower reaches only regard onNext();

        subThreadTest();

    }

    private void subThreadTest() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                Log.d(TAG, "Observable thread is : " + Thread.currentThread().getName());
                Log.d(TAG, "emit 1");
                e.onNext(1);
            }
        }).subscribeOn(Schedulers.newThread())//
                /**
                 *  Modifies an ObservableSource to perform its emissions and notifications on a specified {@link Scheduler},
                 * asynchronously with an unbounded buffer with {@link Flowable#bufferSize()} "island size"
                 */
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.d(TAG, "Observer thread is :" + Thread.currentThread().getName()); // result: main thread
                        Log.d(TAG, "onNext: " + integer);
                    }
                });


//         D/TAG: Observable thread is : RxNewThreadScheduler-2
//         D/TAG: emit 1
//         D/TAG: Observer thread is :main  //接受为主线程
//         D/TAG: onNext: 1


        /**
         * Note:
         * 1. add subscribeOn.
         * 2. subscribeOn appoints thread for event emitting.
         * 3. observerOn appoints thread for event receiving
         *
         * 多次指定上游的线程只有第一次指定的有效, 也就是说多次调用subscribeOn() 只有第一次的有效, 其余的会被忽略.
         * 多次指定下游的线程是可以的, 也就是说每调用一次observeOn(),下游的线程就会切换一次,适用于线程切换。
         */
    }


    private void consumerTest() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                Log.d(TAG, "emit 1");
                emitter.onNext(1);
                Log.d(TAG, "emit 2");
                emitter.onNext(2);
                Log.d(TAG, "emit 3");
                emitter.onNext(3);
                Log.d(TAG, "emit complete");
                emitter.onComplete();
                Log.d(TAG, "emit 4");
                emitter.onNext(4);
            }
        }).subscribe(new Consumer<Integer>() {  // consumer means which only concern what lower reaches receive
            @Override
            public void accept(Integer integer) throws Exception {
                Log.d(TAG, "onNext: " + integer);
            }
        });

    }

    private void disposalTest() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                Log.d(TAG, "emit 1");
                emitter.onNext(1);
                Log.d(TAG, "emit 2");
                emitter.onNext(2);
                Log.d(TAG, "emit 3");
                emitter.onNext(3);

                Log.d(TAG, "emit complete");
                emitter.onComplete();

                Log.d(TAG, "emit 4");
                emitter.onNext(4);
            }
        }).subscribe(new Observer<Integer>() {
            /**
             * define Disposable object
             *
             */

            private Disposable mDisposable;
            private int i;

            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "subscribe");
                mDisposable = d;
            }

            @Override
            public void onNext(Integer value) {
                Log.d(TAG, "onNext: " + value);
                i++;
                if (i == 2) {
                    Log.d(TAG, "dispose");
                    mDisposable.dispose();//收到第2个事件cut line
                    Log.d(TAG, "isDisposed : " + mDisposable.isDisposed());
                }
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "error");

            }

            @Override
            public void onComplete() {
                Log.d(TAG, "complete");
            }

        });

        /**
         * Result
         D/TAG: subscribe
         D/TAG: emit 1
         D/TAG: onNext: 1
         D/TAG: emit 2
         D/TAG: onNext: 2
         D/TAG: dispose
         D/TAG: isDisposed : true
         D/TAG: emit 3
         D/TAG: emit complete
         D/TAG: emit 4
         *
         */

    }

    private void basicRXTest() {
        /**
         * RXJava 链式调用原型
         */
        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
                e.onComplete();  //upper reaches invoke complete, onNext would be invoked continually,and lower reaches would not receive any event
            }
        });

        Observer<Integer> observer = new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "subscribe"); // lower reaches invoking 1st
            }

            @Override
            public void onNext(Integer value) {  //point of event rcvd
                Log.d(TAG, "" + value);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "error");
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "complete");
            }
        };

        /**
         * 注意: 只有当上游和下游建立连接之后, 上游才会开始发送事件. 也就是调用了subscribe() 方法之后才开始发送事件.
         */
        observable.subscribe(observer);
    }


}
