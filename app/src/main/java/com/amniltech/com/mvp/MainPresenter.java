package com.amniltech.com.mvp;

import android.app.Activity;
import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

import static android.text.TextUtils.isEmpty;

public class MainPresenter {
    private MainView mainView;
    private static final String TAG = "MainPresenter";
    private Context mContext;

    public MainPresenter(MainView mainView){
        this.mainView = mainView;
        this.mContext = mainView.getContext();
    }


    public void onCreate(){
        showBtnClick();
        fabBtnClick();

    }

    private void showBtnClick() {
       mainView.observeShowButton().subscribe(o->{
          mainView.calCulateHealthStatus();
       });

    }


    private void fabBtnClick(){
        mainView.observeFab()
                .subscribe(o ->mainView.showSnackbar("Sorry, There are no history yet."));

    }

}
