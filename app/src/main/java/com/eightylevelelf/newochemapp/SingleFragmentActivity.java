package com.eightylevelelf.newochemapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Rustam on 19.06.2016.
 */
public abstract class SingleFragmentActivity extends AppCompatActivity {

    protected abstract Fragment CreateFragment();

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_fragment_container);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.single_fragment_container);

        if (fragment == null)
        {
            fragment = CreateFragment();
            fm.beginTransaction()
                    .add(R.id.single_fragment_container, fragment)
                    .commit();
        }
    }
}