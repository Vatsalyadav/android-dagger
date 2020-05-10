package com.vatsalyadav.apps.daggermvvm;

import android.os.Bundle;
import android.widget.Toast;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class AuthActivity extends DaggerAppCompatActivity {

    @Inject
    String ahahahahaha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        Toast.makeText(this, ahahahahaha, Toast.LENGTH_LONG).show();
    }
}
