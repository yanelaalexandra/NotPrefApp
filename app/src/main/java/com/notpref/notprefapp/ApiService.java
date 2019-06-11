package com.notpref.notprefapp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    //Sin slash al final de la url
    String API_BASE_URL = "https://conasti-app-v3-sebasraureyes.c9users.io";

    @GET("/todo/listar")
    Call<List<Actividad>> getActividades();

}
