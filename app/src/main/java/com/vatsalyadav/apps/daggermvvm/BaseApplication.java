package com.vatsalyadav.apps.daggermvvm;


import com.vatsalyadav.apps.daggermvvm.di.DaggerAppComponent;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;

public class BaseApplication extends DaggerApplication {
    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder().application(this).build(); // Binding an application instance to application component
    }
}
