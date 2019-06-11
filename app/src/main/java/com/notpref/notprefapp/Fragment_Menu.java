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


public class Fragment_Menu extends Fragment {

    BottomNavigationView navView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment__menu, container, false);

        //Colocando el fragment y declarandon en bottomNavigationView
        navView = view.findViewById(R.id.nav_view);
        final FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.container_fragment_menu, new Fragment_Estu_Menu(), "Fragment_Noticias").commit();
        navView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()) {
                    case R.id.navigation_estu_menu:

                        fragmentManager.beginTransaction().replace(R.id.container_fragment_menu, new Fragment_Estu_Menu(), "Fragment_Noticias").commit();

                        return true;
                    case R.id.navigation_eje_menu:
                        fragmentManager.beginTransaction().replace(R.id.container_fragment_menu, new Fragment_Estu_Menu(), "Fragment_Noticias").commit();
                        return true;
                }
                return false;
            }
        });


        return view;
    }

}
