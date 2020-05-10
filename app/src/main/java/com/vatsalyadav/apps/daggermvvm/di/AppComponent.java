package com.vatsalyadav.apps.daggermvvm.di;

import android.app.Application;

import com.vatsalyadav.apps.daggermvvm.BaseApplication;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Component(
        modules = {
                AndroidSupportInjectionModule.class,
                ActivityBuildersModule.class,
                AppModule.class
        }
)
public interface AppComponent extends AndroidInjector<BaseApplication> {

    @Component.Builder
            // override Builder method
    interface Builder {

        @BindsInstance // if you want to bind a particular object through the instance at the time of its construction
        Builder application(Application application);

        AppComponent build(); // mandatory step
    }
}
