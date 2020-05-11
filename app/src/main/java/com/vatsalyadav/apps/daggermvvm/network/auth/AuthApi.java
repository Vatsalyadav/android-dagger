package com.vatsalyadav.apps.daggermvvm.network.auth;

import com.vatsalyadav.apps.daggermvvm.model.User;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface AuthApi {

    @GET("users/{id}")
    Flowable<User> getUser(
            @Path("id") int id  // path in GET and Path should match
    );
}
