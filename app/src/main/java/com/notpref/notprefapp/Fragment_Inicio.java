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
import android.widget.Toast;


public class Fragment_Inicio extends Fragment {

    private Context context;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    BottomNavigationView navView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment__inicio, container, false);

        navView = view.findViewById(R.id.nav_view);

        final FragmentManager fragmentManager = getFragmentManager();

        fragmentManager.beginTransaction().replace(R.id.container_fragment_inicio, new Fragment_Inicio2(), "Fragment_Noticias").commit();


        navView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {


                switch (menuItem.getItemId()) {
                    case R.id.navigation_home:

                        fragmentManager.beginTransaction().replace(R.id.container_fragment_inicio, new Fragment_Inicio2(), "Fragment_Noticias").commit();

                        return true;
                    case R.id.navigation_dashboard:
                        fragmentManager.beginTransaction().replace(R.id.container_fragment_inicio, new Fragment_Favorito(), "Fragment_Noticias").commit();
                        return true;
                    case R.id.navigation_notifications:
                        fragmentManager.beginTransaction().replace(R.id.container_fragment_inicio, new Fragment_Reservas2(), "Fragment_Noticias").commit();

                        return true;
                }
                return false;
            }
        });


        return view;
    }


}
