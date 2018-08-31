package com.pineapple.davinci.activities;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.support.design.widget.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.pineapple.davinci.R;
import com.pineapple.davinci.resources.BottomNavigationViewHelper;
import com.pineapple.davinci.resources.Singleton;

public class Activity_MainPages extends AppCompatActivity
        implements Fragment_Dashboard.OnFragmentInteractionListener,
        Fragment_Calendar.OnFragmentInteractionListener,
        Fragment_eClass.OnFragmentInteractionListener,
        Fragment_Clubs.OnFragmentInteractionListener,
        Fragment_Profile.OnFragmentInteractionListener {

    BottomNavigationView bottomNavigationView;
    Fragment dashboardFragment;
    Fragment calendarFragment;
    Fragment eClassFragment;
    Fragment clubsFragment;
    Fragment profileFragment;

    final FragmentManager fragmentManager = getSupportFragmentManager();
    Fragment active;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_pages);
        //initialize fragments
        dashboardFragment = new Fragment_Dashboard();
        calendarFragment = new Fragment_Calendar();
        eClassFragment = new Fragment_eClass();
        clubsFragment = new Fragment_Clubs();
        profileFragment = new Fragment_Profile();
        //end fragments

        fragmentManager.beginTransaction().add(R.id.fragMainContainer, calendarFragment, "calendar fragment").hide(calendarFragment).commit();
        fragmentManager.beginTransaction().add(R.id.fragMainContainer, eClassFragment, "eClass fragment").hide(eClassFragment).commit();
        fragmentManager.beginTransaction().add(R.id.fragMainContainer, clubsFragment, "clubs fragment").hide(clubsFragment).commit();
        fragmentManager.beginTransaction().add(R.id.fragMainContainer, profileFragment, "profile fragment").hide(profileFragment).commit();
        fragmentManager.beginTransaction().add(R.id.fragMainContainer, dashboardFragment, "dashboard fragment").show(dashboardFragment).commit();
        active = dashboardFragment;



        bottomNavigationView = findViewById(R.id.bottom_navigation);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(0);
        menuItem.setChecked(true);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()) {
                    case R.id.navigation_dashboard:
                        fragmentManager.beginTransaction().replace(R.id.fragMainContainer,dashboardFragment).commit();
                        return true;
                    case R.id.navigation_calendar:
                        fragmentManager.beginTransaction().replace(R.id.fragMainContainer,calendarFragment).commit();
                        return true;
                    case R.id.navigation_eclass:
                        fragmentManager.beginTransaction().replace(R.id.fragMainContainer,eClassFragment).commit();
                        return true;
                    case R.id.navigation_clubs:
                        fragmentManager.beginTransaction().replace(R.id.fragMainContainer,clubsFragment).commit();
                        return true;
                    case R.id.navigation_profile:
                        fragmentManager.beginTransaction().replace(R.id.fragMainContainer,profileFragment).commit();
                        return true;
                }
            return false;
            }
        });
    }


    @Override
    public void onFragmentInteraction(Uri uri) {
        //hi
    }
}
