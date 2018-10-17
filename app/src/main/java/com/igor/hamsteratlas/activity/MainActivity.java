package com.igor.hamsteratlas.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.igor.hamsteratlas.R;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;

public class MainActivity extends AppCompatActivity {
    private NavController mNavController;

    public NavController getNavController() {
        return mNavController;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mNavController = Navigation.findNavController(this, R.id.my_nav_host_fragment);
    }
}
