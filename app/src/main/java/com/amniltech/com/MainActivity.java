package com.amniltech.com;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.amniltech.com.mvp.MainPresenter;
import com.amniltech.com.mvp.MainView;

import javax.inject.Inject;

public class MainActivity extends BaseAppCompatActivity {

    @Inject
    MainView mainView;

    @Inject
    MainPresenter mainPresenter;

    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appComponent.inject(this);
        setContentView(mainView);
        mainPresenter.onCreate();

    }

}
