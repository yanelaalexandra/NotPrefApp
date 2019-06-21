package com.notpref.notprefapp;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActividadesAdapter extends RecyclerView.Adapter<ActividadesAdapter.ViewHolder> {


    private List<Actividad> actividads;
    ApiService service = ApiServiceGenerator.createService(ApiService.class);
    Context ctx;

    public ActividadesAdapter(){
        this.actividads = new ArrayList<>();
    }

    public void setProductos(List<Actividad> productos){
        this.actividads = productos;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView titulo_actividad, tipo_actividad;
        CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);
            titulo_actividad = itemView.findViewById(R.id.titulo_actividad);
            tipo_actividad = itemView.findViewById(R.id.tipo_actividad);
            cardView = itemView.findViewById(R.id.card_view);

        }
    }



    @NonNull
    @Override
    public ActividadesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_cardview, viewGroup, false);

        ctx = viewGroup.getContext();
        return new ViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull final ActividadesAdapter.ViewHolder viewHolder, int i) {

        final Actividad actividad = this.actividads.get(i);
        final Dialog myDialog;

        //Codigo para el popup
        myDialog = new Dialog(ctx);

        viewHolder.titulo_actividad.setText(actividad.getTitulo());

        final String titulo2 = actividad.getTitulo();
        final String descripcion = actividad.getContenido();
        final String fecha_creacion = actividad.getFechaEvento();
        final String hora_creacion = actividad.getHora();
        final String uuid = actividad.getId();
        if(actividad.getEvento().equals("true")){





            Call<ResponseMessage> call = service.contadorVistas(uuid);
            call.enqueue(new Callback<ResponseMessage>() {
                @Override
                public void onResponse(Call<ResponseMessage> call, Response<ResponseMessage> response) {

                }

                @Override
                public void onFailure(Call<ResponseMessage> call, Throwable t) {

                }
            });


            viewHolder.tipo_actividad.setText("Evento");
            viewHolder.titulo_actividad.setTextColor(Color.parseColor("#993692F4"));
            //viewHolder.tipo_actividad.setTextColor(Color.parseColor("#993692F4"));
            //viewHolder.cardView.setBackgroundResource(R.color.card_evento);


            viewHolder.titulo_actividad.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TextView txtclose, titulo, contenido, fecha, hora;
                    Button btnFollow;
                    myDialog.setContentView(R.layout.card_popup);
                    txtclose =(TextView) myDialog.findViewById(R.id.txtclose);
                    titulo = myDialog.findViewById(R.id.titulo_popup);
                    contenido = myDialog.findViewById(R.id.desc_evento);
                    hora = myDialog.findViewById(R.id.hora_creacion);
                    fecha = myDialog.findViewById(R.id.fecha_creacion);

                    Button btnFavorito = myDialog.findViewById(R.id.btn_fav);
                    Button btnReservado = myDialog.findViewById(R.id.btn_reser);

                    txtclose.setText("X");
                    titulo.setText(titulo2);
                    contenido.setText(descripcion);
                    hora.setText(hora_creacion);
                    fecha.setText(fecha_creacion);



                    btnFavorito.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            Call<ResponseMessage> call = service.cambiarReservado(uuid);
                            call.enqueue(new Callback<ResponseMessage>() {
                                @Override
                                public void onResponse(Call<ResponseMessage> call, Response<ResponseMessage> response) {
                                    Toast.makeText(ctx, "Agregando...", Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onFailure(Call<ResponseMessage> call, Throwable t) {
                                    Toast.makeText(ctx, "Error...", Toast.LENGTH_SHORT).show();
                                    Log.i("Error", t.toString());
                                }
                            });

                        }
                    });

                    btnReservado.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Call<ResponseMessage> call = service.contadorConfirmacionesConfirmar(uuid);
                            call.enqueue(new Callback<ResponseMessage>() {
                                @Override
                                public void onResponse(Call<ResponseMessage> call, Response<ResponseMessage> response) {
                                    Toast.makeText(ctx, "Reservando...", Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onFailure(Call<ResponseMessage> call, Throwable t) {
                                    Toast.makeText(ctx, "Error...", Toast.LENGTH_SHORT).show();
                                    Log.i("Error", t.toString());
                                }
                            });
                        }
                    });


                    txtclose.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            myDialog.dismiss();
                        }
                    });
                    myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    myDialog.show();
                }
            });

        }else if (actividad.getEvento().equals("false")){
            viewHolder.tipo_actividad.setText("Noticia");
            viewHolder.titulo_actividad.setTextColor(Color.parseColor("#99F44336"));
            //viewHolder.tipo_actividad.setTextColor(Color.parseColor("#99F44336"));
            //viewHolder.cardView.setBackgroundResource(R.color.card_noticia);

            viewHolder.titulo_actividad.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TextView txtclose, titulo, contenido, fecha, hora;
                    Button btnFollow;
                    myDialog.setContentView(R.layout.card_popup_noticia);
                    txtclose =(TextView) myDialog.findViewById(R.id.txtclose);
                    titulo = myDialog.findViewById(R.id.titulo_popup);
                    contenido = myDialog.findViewById(R.id.desc_noticia);
                    hora = myDialog.findViewById(R.id.hora_creacion);
                    fecha = myDialog.findViewById(R.id.fecha_creacion);

                    txtclose.setText("X");
                    titulo.setText(titulo2);
                    contenido.setText(descripcion);
                    hora.setText(hora_creacion);
                    //fecha.setText(fecha_creacion);

                    txtclose.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            myDialog.dismiss();
                        }
                    });
                    myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    myDialog.show();
                }
            });
        }






    }

    @Override
    public int getItemCount() {
        return this.actividads.size();
    }
}
