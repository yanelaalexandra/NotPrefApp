package com.notpref.notprefapp;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MenuActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    private static final String TAG = "A";

    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);


        drawerLayout = findViewById(R.id.drawer_layout);



        NavigationView navigationView = findViewById(R.id.navigation_view);

        View header = navigationView.getHeaderView(0);
        header.findViewById(R.id.header_title).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MenuActivity.this, "Hola",
                        Toast.LENGTH_SHORT).show();
            }
        });


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);



        FragmentManager fragmentManager = getSupportFragmentManager();

        fragmentManager.beginTransaction().replace(R.id.container, new Fragment_Inicio(), "Fragment_Noticias").commit();

    }



    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {

        int id =  menuItem.getItemId();

        FragmentManager fragmentManager = getSupportFragmentManager();

            if ( id == R.id.nav_inicio) {

                fragmentManager.beginTransaction().replace(R.id.container, new Fragment_Inicio(), "Fragment_Noticias").commit();

            }
            else if (id == R.id.nav_perfil){

                fragmentManager.beginTransaction().replace(R.id.container, new Fragment_Perfil(), "Fragment_Perfil").commit();

            }else if (id == R.id.nav_even){

                fragmentManager.beginTransaction().replace(R.id.container, new Fragment_Eventos(), "Fragment_Noticias").commit();

            }else if (id== R.id.nav_not){

                fragmentManager.beginTransaction().replace(R.id.container, new Fragment_Noticias(), "Fragment_Eventos").commit();
            } else if (id == R.id.nav_menu){

                fragmentManager.beginTransaction().replace(R.id.container, new Fragment_Menu(), "Fragment_Menu").commit();

            } else if (id == R.id.nav_reservas){

                fragmentManager.beginTransaction().replace(R.id.container, new Fragment_Reservas(), "Fragment_Reserva").commit();

            }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    public boolean removerFragment(String fragmentTAG){
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(fragmentTAG);
        if (fragment != null){
            getSupportFragmentManager().beginTransaction().remove(fragment).commit();
            Log.i(TAG,fragment.getTag() + " removed");
            return true;
        }
        return false;
    }
}
