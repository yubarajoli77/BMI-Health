package com.amniltech.com.mvp;

import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.util.Log;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class MainPresenter {
    private MainView mainView;
    private static final String TAG = "MainPresenter";

    public MainPresenter(MainView mainView){
        this.mainView = mainView;
    }

    public void onCreate(){
        showBtnClick();
        fabBtnClick();
    }

    private void showBtnClick() {

        mainView.observableShowButton().subscribe(o->{
            mainView.showMessage("Hello");
        });

    }

    private void fabBtnClick(){
        mainView.observableFab()
                .subscribe(o ->{
                        mainView.showMessage("Clicked");
                });

    }

    private void popUpResult() {
        mainView.showMessage("Button Clicked");
    }

}
