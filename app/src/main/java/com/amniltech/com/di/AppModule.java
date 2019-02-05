package com.amniltech.com.di;

import android.support.v7.app.AppCompatActivity;

import com.amniltech.com.mvp.MainPresenter;
import com.amniltech.com.mvp.MainView;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {
    private AppCompatActivity activity;

    public AppModule(AppCompatActivity activity){
        this.activity = activity;
    }

    @Provides
    public MainView mainView(){ return new MainView(activity);}

    @Provides
    public MainPresenter mainPresenter(MainView mainView){return new MainPresenter(mainView);}

}
