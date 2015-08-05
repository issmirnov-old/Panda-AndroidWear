package com.smirnovlabs.android.panda.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;

import com.smirnovlabs.android.panda.R;
import com.smirnovlabs.android.panda.fragment.CommandsFragment;
import com.smirnovlabs.android.panda.fragment.ControlFragment;
import com.smirnovlabs.android.panda.fragment.NavigationDrawerFragment;
import com.smirnovlabs.android.panda.fragment.SettingsFragment;


public class MainActivity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    private String TAG = "PANDA MOBILE";
    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) { // TODO do injections here to swap out fragments
        // update the main content by replacing fragments
        Fragment fragment;
        FragmentManager fragmentManager = getFragmentManager();

        switch(position) {
            default:
            case 0: // control interface
                fragment = new ControlFragment();
                setTitle(getString(R.string.title_section1));
                break;
            case 1: // list of commands
                fragment = new CommandsFragment();
                setTitle(getString(R.string.title_section2));
                break;
            case 2: // settings
                fragment = new SettingsFragment();
                setTitle(getString(R.string.title_section3));
                break;
        }
        fragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .commit();
    }

}
