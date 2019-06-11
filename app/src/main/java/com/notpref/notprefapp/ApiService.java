package com.notpref.notprefapp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {
    //Sin slash al final de la url
    String API_BASE_URL = "https://conasti-app-v3-sebasraureyes.c9users.io";

    @GET("/todo/listar")
    Call<List<Actividad>> getActividades();

    @GET("/todo/listar/confirmados")
    Call<List<Actividad>> getActividadesConfirmadas();

    @GET("/todo/listar/reservados")
    Call<List<Actividad>> getActividadesReservadas();


    // Actualizar Campos

    @GET("/noticia/reservaciones/{uuid}")
    Call<ResponseMessage> cambiarReservado(@Path("uuid") String uuid);

    @GET("noticia/visualizaciones/{uuid}")
    Call<ResponseMessage> contadorVistas(@Path("uuid") String uuid);

    @GET("/noticia/confirmaciones/{uuid}")
    Call<ResponseMessage> contadorConfirmacionesConfirmar(@Path("uuid") String uuid);

}
