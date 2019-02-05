package com.amniltech.com.di;

import com.amniltech.com.MainActivity;

import dagger.Component;

@Component(modules = AppModule.class)
public interface AppComponent {
    void inject(MainActivity mainActivity);
}
