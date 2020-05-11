package com.vatsalyadav.apps.daggermvvm.ui.auth;

import android.util.Log;

import com.vatsalyadav.apps.daggermvvm.model.User;
import com.vatsalyadav.apps.daggermvvm.network.auth.AuthApi;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import io.reactivex.schedulers.Schedulers;

public class AuthViewModel extends ViewModel {

    private final AuthApi authApi;

    private MediatorLiveData<User> authUser = new MediatorLiveData<>();

    @Inject
    public AuthViewModel(AuthApi authApi) { // Requirement for multi binding
        Log.d("AuthViewModel", "AuthViewModel: is working");
        this.authApi = authApi;
    }

    public void authenticateWithId(int userId){
        final LiveData<User> source = LiveDataReactiveStreams.fromPublisher(
                authApi.getUser(userId)
                .subscribeOn(Schedulers.io())
        );
        // Now how to convert the source to the authUser? That's why we are using MediatorLiveData
        authUser.addSource(source, new Observer<User>() {
            @Override
            public void onChanged(User user) {
                authUser.setValue(user); // it will update any observers
                authUser.removeSource(source); // Since we no longer need to listen to it
            }
        });
    }

    public LiveData<User> observeUser() {
        return authUser;
    }
}
