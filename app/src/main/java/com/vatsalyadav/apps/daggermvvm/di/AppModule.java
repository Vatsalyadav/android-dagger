package com.vatsalyadav.apps.daggermvvm.di;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {
    // App level module which will have Application level dependencies like Retrofit, Glide Instance
    // or anything whose instance won't change throughout the lifetime of the application

    @Provides
    static String someString() {
        return "Hello World";
    }

//    @Provides
//    static boolean getApp(Application application) { // application is available because we have bound the application in
//        // Builder application(Application application); otherwise it will return false
//        return application == null;
//    }


}
