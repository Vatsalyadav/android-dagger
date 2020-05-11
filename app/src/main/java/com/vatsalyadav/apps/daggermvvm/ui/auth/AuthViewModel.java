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
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class AuthViewModel extends ViewModel {

    private final AuthApi authApi;

    private MediatorLiveData<AuthResource<User>> authUser = new MediatorLiveData<>();

    @Inject
    public AuthViewModel(AuthApi authApi) { // Requirement for multi binding
        Log.d("AuthViewModel", "AuthViewModel: is working");
        this.authApi = authApi;
    }

    public void authenticateWithId(int userId){
        authUser.setValue(AuthResource.loading((User)null)); // will trigger the progress bar
        final LiveData<AuthResource<User>> source = LiveDataReactiveStreams.fromPublisher(
                authApi.getUser(userId)
                        .onErrorReturn(new Function<Throwable, User>() {
                            @Override
                            public User apply(Throwable throwable) throws Exception {
                                User errorUser = new User();
                                errorUser.setId(-1);
                                return errorUser;
                            }
                        })
                        .map(new Function<User, AuthResource<User>>() {
                            @Override
                            public AuthResource<User> apply(User user) throws Exception {
                                if(user.getId() == -1){
                                    return AuthResource.error("Could not authenticate", (User)null);
                                }
                                return AuthResource.authenticated(user);
                            }
                        })
                .subscribeOn(Schedulers.io())
        );
//         Now how to convert the source to the authUser? That's why we are using MediatorLiveData
        authUser.addSource(source, new Observer<AuthResource<User>>() {
            @Override
            public void onChanged(AuthResource<User> user) {
                authUser.setValue(user); // it will update any observers
                authUser.removeSource(source); // Since we no longer need to listen to it
            }
        });
    }

    public MediatorLiveData<AuthResource<User>> observeUser() {
        return authUser;
    }
}
