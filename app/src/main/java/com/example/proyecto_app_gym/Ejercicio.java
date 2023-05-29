package com.example.proyecto_app_gym;

public class Ejercicio {
    String musculo;
    String dificultad;
    int repeticiones;
    int velocidad;
    int tiempo;
    public String getMusculo() {
        return musculo;
    }

    @Override
    public String toString() {
        return "Ejercicio{" +
                "musculo='" + musculo + '\'' +
                ", dificultad='" + dificultad + '\'' +
                ", repeticiones=" + repeticiones +
                ", velocidad=" + velocidad +
                ", tiempo=" + tiempo +
                '}';
    }

    public void setMusculo(String musculo) {
        this.musculo = musculo;
    }

    public String getDificultad() {
        return dificultad;
    }

    public void setDificultad(String dificultad) {
        this.dificultad = dificultad;
    }

    public int getRepeticiones() {
        return repeticiones;
    }

    public void setRepeticiones(int repeticiones) {
        this.repeticiones = repeticiones;
    }

    public int getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }

    public int getTiempo() {
        return tiempo;
    }

    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }


    public Ejercicio() {
    }

}
