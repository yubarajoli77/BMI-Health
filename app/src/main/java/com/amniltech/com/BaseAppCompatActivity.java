package com.amniltech.com;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.amniltech.com.di.AppComponent;
import com.amniltech.com.di.AppModule;
import com.amniltech.com.di.DaggerAppComponent;

public abstract class BaseAppCompatActivity extends AppCompatActivity {
    public AppComponent appComponent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();

    }
}
