package com.example.proyecto_app_gym;

public class Rutina {
    String email;
    String DiaDeSemana="";
    String Ejercicio1=null;
    String Ejercicio2=null;
    String Ejercicio3=null;
    String Ejercicio4=null;
    String Ejercicio5=null;
    String EjercicioCardioVascular;

    public String getEjercicioCardioVascular() {
        return EjercicioCardioVascular;
    }

    public void setEjercicioCardioVascular(String ejercicioCardioVascular) {
        EjercicioCardioVascular = ejercicioCardioVascular;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDiaDeSemana() {
        return DiaDeSemana;
    }

    public void setDiaDeSemana(String diaDeSemana) {
        DiaDeSemana = diaDeSemana;
    }

    public String getEjercicio1() {
        return Ejercicio1;
    }

    public void setEjercicio1(String ejercicio1) {
        Ejercicio1 = ejercicio1;
    }

    public String getEjercicio2() {
        return Ejercicio2;
    }

    public void setEjercicio2(String ejercicio2) {
        Ejercicio2 = ejercicio2;
    }

    public String getEjercicio3() {
        return Ejercicio3;
    }

    public void setEjercicio3(String ejercicio3) {
        Ejercicio3 = ejercicio3;
    }

    public String getEjercicio4() {
        return Ejercicio4;
    }

    public void setEjercicio4(String ejercicio4) {
        Ejercicio4 = ejercicio4;
    }

    public String getEjercicio5() {
        return Ejercicio5;
    }

    public void setEjercicio5(String ejercicio5) {
        Ejercicio5 = ejercicio5;
    }



    public Rutina(String mail) {

    }
    @Override
    public String toString() {
        return "Rutina{" +
                "email='" + email + '\'' +
                ", DiaDeSemana='" + DiaDeSemana + '\'' +
                ", Ejercicio1='" + Ejercicio1 + '\'' +
                ", Ejercicio2='" + Ejercicio2 + '\'' +
                ", Ejercicio3='" + Ejercicio3 + '\'' +
                ", Ejercicio4='" + Ejercicio4 + '\'' +
                ", Ejercicio5='" + Ejercicio5 + '\'' +
                ", EjercicioCardioVascular='" + EjercicioCardioVascular + '\'' +
                '}';
    }


}
