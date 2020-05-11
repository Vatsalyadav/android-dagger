package com.vatsalyadav.apps.daggermvvm.ui.auth;

import android.util.Log;

import com.vatsalyadav.apps.daggermvvm.network.auth.AuthApi;

import javax.inject.Inject;

import androidx.lifecycle.ViewModel;

public class AuthViewModel extends ViewModel {

    private final AuthApi authApi;

    @Inject
    public AuthViewModel(AuthApi authApi){ // Requirement for multi binding
        Log.d("AuthViewModel", "AuthViewModel: is working");
        this.authApi = authApi;
        if (this.authApi == null){
            Log.d("AuthViewModel", "AuthApi is null");
        }
        else     Log.d("AuthViewModel", "AuthApi is not null");
    }
}
