package com.notpref.notprefapp;

public class Actividad {

    String contenido;
    String enviado;
    String estado;
    String evento;
    String fecha;
    String hora;
    String titulo;

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public String getEnviado() {
        return enviado;
    }

    public void setEnviado(String enviado) {
        this.enviado = enviado;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getEvento() {
        return evento;
    }

    public void setEvento(String evento) {
        this.evento = evento;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    @Override
    public String toString() {
        return "Actividad{" +
                "contenido='" + contenido + '\'' +
                ", enviado='" + enviado + '\'' +
                ", estado='" + estado + '\'' +
                ", evento='" + evento + '\'' +
                ", fecha='" + fecha + '\'' +
                ", hora='" + hora + '\'' +
                ", titulo='" + titulo + '\'' +
                '}';
    }
}
