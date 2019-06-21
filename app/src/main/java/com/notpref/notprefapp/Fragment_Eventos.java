package com.notpref.notprefapp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Fragment_Eventos extends Fragment {

    BottomNavigationView navView;
    TextView titulo_bottom;
    private static final String TAG = Fragment_Inicio2.class.getSimpleName();
    private RecyclerView actividadesList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment__eventos, container, false);


        //codigo para el recycleview
        actividadesList = view.findViewById(R.id.recyclerview_res);
        actividadesList.setLayoutManager(new LinearLayoutManager(getContext()));
        actividadesList.setAdapter(new ActividadesAdapterRes());

        // Inflate the layout for this fragment
        initialize();

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

    private void initialize() {

        ApiService service = ApiServiceGenerator.createService(ApiService.class);

        Call<List<Actividad>> call = service.getActividadesConfirmadas();

        call.enqueue(new Callback<List<Actividad>>() {
            @Override
            public void onResponse(Call<List<Actividad>> call, Response<List<Actividad>> response) {
                try {

                    int statusCode = response.code();
                    Log.d(TAG, "HTTP status code: " + statusCode);

                    if (response.isSuccessful()) {

                        List<Actividad> actividads = response.body();
                        Log.d(TAG, "productos: " + actividads);

                        ActividadesAdapterFav adapter = (ActividadesAdapterFav) actividadesList.getAdapter();
                        adapter.setProductos(actividads);
                        adapter.notifyDataSetChanged();

                    } else {
                        Log.e(TAG, "onError: " + response.errorBody().string());
                        throw new Exception("Error en el servicio");
                    }

                } catch (Throwable t) {
                    try {
                        Log.e(TAG, "onThrowable: " + t.toString(), t);
                        Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                    }catch (Throwable x){}
                }
            }

            @Override
            public void onFailure(Call<List<Actividad>> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.toString());
                Toast.makeText(getContext(),t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }




}
