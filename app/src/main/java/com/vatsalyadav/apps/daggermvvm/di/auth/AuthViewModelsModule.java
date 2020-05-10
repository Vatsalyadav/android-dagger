package com.vatsalyadav.apps.daggermvvm.di.auth;

import com.vatsalyadav.apps.daggermvvm.di.ViewModelKey;
import com.vatsalyadav.apps.daggermvvm.ui.auth.AuthViewModel;

import androidx.lifecycle.ViewModel;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class AuthViewModelsModule {
    // Responsible for dependency for AuthViewModelsModule itself

    @Binds
    @IntoMap
    @ViewModelKey(AuthViewModel.class)
    public abstract ViewModel bindAuthViewModel(AuthViewModel viewModel);
}
