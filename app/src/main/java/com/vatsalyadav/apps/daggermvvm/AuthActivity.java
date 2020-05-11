package com.vatsalyadav.apps.daggermvvm;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.RequestManager;
import com.vatsalyadav.apps.daggermvvm.model.User;
import com.vatsalyadav.apps.daggermvvm.ui.auth.AuthViewModel;
import com.vatsalyadav.apps.daggermvvm.viewmodels.ViewModelProviderFactory;

import javax.inject.Inject;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import dagger.android.support.DaggerAppCompatActivity;

public class AuthActivity extends DaggerAppCompatActivity implements View.OnClickListener{

    private static final String TAG = "AuthActivity";
    @Inject
    ViewModelProviderFactory providerFactory;
    private AuthViewModel viewModel;
    private EditText userId;
    @Inject
    Drawable logo;

    @Inject
    RequestManager requestManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        userId = findViewById(R.id.user_id_input);
        findViewById(R.id.login_button).setOnClickListener(this);

        viewModel = new ViewModelProvider(this, providerFactory).get(AuthViewModel.class);

        setLogo();

        subscribeObserver();
    }

    private void setLogo() {
        requestManager
                .load(logo)
                .into((ImageView) findViewById(R.id.login_logo));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.login_button:
                attemptLogin();
                break;
        }
    }

    private void attemptLogin(){
        if(TextUtils.isEmpty(userId.getText().toString())){
            return;
        }
        viewModel.authenticateWithId(Integer.parseInt(userId.getText().toString()));
    }

    private void subscribeObserver(){
        viewModel.observeUser().observe(this, new Observer<User>() {
            @Override
            public void onChanged(User user) {
                if(user != null){
                    Log.d(TAG, "onChanged: "+user.getEmail());
                }
            }
        });
    }
}
