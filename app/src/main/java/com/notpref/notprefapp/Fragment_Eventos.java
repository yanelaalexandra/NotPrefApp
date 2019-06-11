package com.notpref.notprefapp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;


public class Fragment_Eventos extends Fragment {

    BottomNavigationView navView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment__eventos, container, false);

        //Colocando el fragment y declarandon en bottomNavigationView
        navView = view.findViewById(R.id.nav_view);
        final FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.container_fragment_eventos, new Fragment_Fav_Eventos(), "Fragment_Noticias").commit();
        navView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()) {
                    case R.id.navigation_fav_eventos:

                        fragmentManager.beginTransaction().replace(R.id.container_fragment_eventos, new Fragment_Fav_Eventos(), "Fragment_Noticias").commit();

                        return true;
                    case R.id.navigation_reser_eventos:
                        fragmentManager.beginTransaction().replace(R.id.container_fragment_eventos, new Fragment_Reser_Eventos(), "Fragment_Noticias").commit();
                        return true;
                }
                return false;
            }
        });

        return view;
    }




}
