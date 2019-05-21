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

    private TextView mTextMessage;

    private DrawerLayout drawerLayout;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:

                    return true;
                case R.id.navigation_dashboard:

                    return true;
                case R.id.navigation_notifications:

                    return true;
            }
            return false;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        BottomNavigationView navView = findViewById(R.id.nav_view);

        drawerLayout = findViewById(R.id.drawer_layout);

        mTextMessage = findViewById(R.id.message);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

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

                removerFragment("Fragment_Perfil");
                removerFragment("Fragment_Noticias");
                removerFragment("Fragment_Eventos");
                removerFragment("Fragment_Menu");
                removerFragment("Fragment_Reserva");

            }
            else if (id == R.id.nav_perfil){

                //Fragment para el perfil de usuario
                fragmentManager.beginTransaction().replace(R.id.container, new Fragment_Perfil(), "Fragment_Perfil").commit();
                Toast.makeText(MenuActivity.this, "PERFIL", Toast.LENGTH_SHORT).show();

            }else if (id == R.id.nav_even){

                fragmentManager.beginTransaction().replace(R.id.container, new Fragment_Noticias(), "Fragment_Noticias").commit();

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
