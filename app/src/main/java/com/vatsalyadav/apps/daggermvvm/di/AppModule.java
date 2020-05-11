package com.vatsalyadav.apps.daggermvvm.di;

import android.app.Application;
import android.graphics.drawable.Drawable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.vatsalyadav.apps.daggermvvm.R;
import com.vatsalyadav.apps.daggermvvm.util.Constants;

import javax.inject.Singleton;

import androidx.core.content.ContextCompat;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class AppModule {
    // App level module which will have Application level dependencies like Retrofit, Glide Instance
    // or anything whose instance won't change throughout the lifetime of the application

    @Singleton // Since AppComponent owns the singleton scope, these will live Application wise
    @Provides
    static RequestOptions provideRequestOptions() {
        return RequestOptions
                .placeholderOf(R.drawable.white_background)
                .error(R.drawable.white_background);
    }

    @Singleton
    @Provides
    static RequestManager provideGlideInstance(Application application, RequestOptions requestOptions) { // Available since we are providing above
        return Glide.with(application)
                .setDefaultRequestOptions(requestOptions);
    }

    @Singleton
    @Provides
    static Drawable provideAppDrawable(Application application) {
        return ContextCompat.getDrawable(application, R.drawable.logo);
    }

    @Singleton
    @Provides
    static Retrofit provideRetrofitInstance() {
        return new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
