package com.example.proyecto_app_gym;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;

public class TomaDeDatos extends AppCompatActivity {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    Button btnSave,btnCrear;
    String email;

    EditText etAltura, etPeso, etActividad, etEdad;
    int edad;
    int altura;
    int peso;
    int actividad;
    int diasEntreno = 0;
    boolean cardio = false;
    String dificultad = null;
    ArrayList<Rutina> DiasDeRutina = new ArrayList<Rutina>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toma_de_datos);
        TextView texto = (TextView) findViewById(R.id.textViewemail);
        email = getIntent().getExtras().getString("email");
        texto.setText(texto.getText() + " " + email);

        etEdad = findViewById(R.id.editTextEdad);
        etPeso = findViewById(R.id.editTextPeso);
        etActividad = findViewById(R.id.editTextActividad);
        etAltura = findViewById(R.id.editTextAltura);
        btnCrear=findViewById(R.id.buttonRutina);

        btnSave = findViewById(R.id.buttonSave);


        btnCrear.setOnClickListener(view -> {
            crearRutina();
        });
    }

    private void crearRutina(){
        Intent i = new Intent(this, PlanDeEntreno.class);
        i.putExtra("email", email);
        Toast.makeText(TomaDeDatos.this, "Creando rutina", Toast.LENGTH_SHORT).show();
        startActivity(i);
    }
    public void guardar(View view) {
        if (TextUtils.isEmpty(etEdad.getText())) {
            etEdad.setError("el campo es obligatorio");
            etEdad.requestFocus();
        } else if (TextUtils.isEmpty(etPeso.getText())) {
            etPeso.setError("el campo es obligatorio");
            etPeso.requestFocus();
        } else if (TextUtils.isEmpty(etAltura.getText())) {
            etAltura.setError("el campo es obligatorio");
            etAltura.requestFocus();
        } else if (TextUtils.isEmpty(etActividad.getText())) {
            etActividad.setError("el campo es obligatorio");
            etActividad.requestFocus();
        } else {


            edad = Integer.parseInt(etEdad.getText().toString().trim());
            altura = Integer.parseInt(etAltura.getText().toString().trim());
            peso = Integer.parseInt(etPeso.getText().toString().trim());
            actividad = Integer.parseInt(etActividad.getText().toString().trim());

            HashMap<String, Integer> datos = new HashMap<>();
            datos.put("peso", peso);
            datos.put("altura", altura);
            datos.put("edad", edad);
            datos.put("actividadFisica", actividad);
            db.collection("users").document(email).set(datos);
            if (diasEntreno == 0)
                asignacionRequerimientos();

            asignacionEjercicio();
        }


    }

    private void asignacionRequerimientos() {
        //criterios a la hora de la seleccion de ejercicios
        //formula calculo imc
        double imc = peso / ((altura * 2) / 100);


        if (imc > 25)
            cardio = true;
        //la edad establece la dificultad del ejercicio

        if ((edad < 18 || edad > 65) && actividad < 3)
            dificultad = "baja";
        else if ((edad < 18 || edad > 65) && actividad >= 3)
            dificultad = "media";
        else if ((edad > 18 || edad < 65) && actividad < 3)
            dificultad = "media";
        else if ((edad > 18 || edad < 65) && actividad >= 3)
            dificultad = "alta";

        if (actividad < 3)
            diasEntreno = 3;
        else if (actividad == 5)
            diasEntreno = 5;
        else
            diasEntreno = 4;


        //UNA VEZ CONOCIDAS LAS CAPACIDADES DE LA PERSONA, ESTABLECEREMOS LA RUTINA Y LOS DIAS DE LA SEMANA QUE ENTRENARA


        for (int i = 0; i < diasEntreno; i++) {
            Rutina r = new Rutina(email);
            DiasDeRutina.add(r);

        }
    }

    private void asignacionEjercicio() {
        asignacionCardio();//este metodo se encarga de comprobar si el usuario necesita hacer ejercicio cardiovascular y asignarlo en la rutina
        if (DiasDeRutina.size() == 3) {
            //AL TENER 3 DIAS DE ENTRENO LO DIVIDIDIREMOS  A LO LARGO DE LA SEMANA

            DiasDeRutina.get(0).setDiaDeSemana("lunes");
            DiasDeRutina.get(1).setDiaDeSemana("miercoles");
            DiasDeRutina.get(2).setDiaDeSemana("viernes");
            //rellenamos la rutina del usuario en funcion de los dias de entreno, como entrena 3 dias pues hay que repartir la rutina y no tener dias de musculos muy especificos
            rutinaEmpuje(0);
            rutinaTiron(1);
            rutinaPierna(2);
        }
        if (DiasDeRutina.size() == 4) {
            //AL TENER 3 DIAS DE ENTRENO LO DIVIDIDIREMOS  A LO LARGO DE LA SEMANA
            DiasDeRutina.get(0).setDiaDeSemana("lunes");
            DiasDeRutina.get(1).setDiaDeSemana("martes");
            DiasDeRutina.get(2).setDiaDeSemana("jueves");
            DiasDeRutina.get(3).setDiaDeSemana("viernes");
            //en este caso como son cuatro dias podemos añadir un dia especifico de brazo
            rutinaEmpuje(0);
            rutinaTiron(1);
            rutinaPierna(2);
            rutinaBrazo(3);
        }
        if (DiasDeRutina.size() == 5) {
            //AL TENER 3 DIAS DE ENTRENO LO DIVIDIDIREMOS  A LO LARGO DE LA SEMANA
            DiasDeRutina.get(0).setDiaDeSemana("lunes");
            DiasDeRutina.get(1).setDiaDeSemana("martes");
            DiasDeRutina.get(2).setDiaDeSemana("miercoles");
            DiasDeRutina.get(3).setDiaDeSemana("jueves");
            DiasDeRutina.get(4).setDiaDeSemana("viernes");
            //en este caso como son cinco dias añadiremos 2 dias de pierna ya que es un musculo muy grande
            rutinaEmpuje(0);
            rutinaTiron(1);
            rutinaPierna(2);
            rutinaBrazo(3);
            rutinaPierna(4);

        }


        HashMap<String, String> DatosRutina = new HashMap<>();
        int contador = 1;
        //System.out.println("estoy fuera"+DiasDeRutina.size());
        for (Rutina r : DiasDeRutina) {

            //System.out.println("estoy dentro");
            DatosRutina.put("email", r.getEmail());
            DatosRutina.put("dia", r.getDiaDeSemana());
            DatosRutina.put("Cardio", r.EjercicioCardioVascular);
            DatosRutina.put("Ejercicio1", r.getEjercicio1());
            DatosRutina.put("Ejercicio2", r.getEjercicio2());
            DatosRutina.put("Ejercicio3", r.getEjercicio3());
            DatosRutina.put("Ejercicio4", r.getEjercicio4());
            DatosRutina.put("Ejercicio5", r.getEjercicio5());
            db.collection("Rutinas").document(r.getDiaDeSemana()+"_" + contador+email).set(DatosRutina);
            contador++;
            //System.out.println("soy contador"+contador);
        }
    }


    private void rutinaTiron(int dia) {//este parametro nos ayuda a poder usar este metodo cada vez que alguien tenga ejercicios de tiron en su rutina

        db.collection("ejercicios").whereEqualTo("musculo", "espalda").whereEqualTo("dificultad", dificultad).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                int contador = 0;//esta variable la vamos a usar para establecer los ejercicios de un grupo musculr, si queremos 2 ejercicios en la rutina pues tendremos que recorrer 2 posciciones del task.result
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {

                        //aunque este en un for solo da una vuelta porque solo devuelve un ejercicio pero a la hora de la lectura es necesario
                        //añadimos estas condiciones porque solo queremos 2 ejercicios de este musculo, por lo que solo guardamos el primer y segundo valor de la consulta
                        if (contador == 0)
                            DiasDeRutina.get(dia).setEjercicio1(document.getId());
                        else if (contador == 1)
                            DiasDeRutina.get(dia).setEjercicio2(document.getId());
                        else if (contador == 2)
                            DiasDeRutina.get(dia).setEjercicio3(document.getId());
                        contador++;
                    }

                } else {
                    Log.d(TAG, "Error getting documents: ", task.getException());
                }
            }
        });
        db.collection("ejercicios").whereEqualTo("musculo", "biceps").whereEqualTo("dificultad", "media").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                int contador = 0;
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        if (contador == 0)
                            DiasDeRutina.get(dia).setEjercicio4(document.getId());
                        else if (contador == 1)
                            DiasDeRutina.get(dia).setEjercicio5(document.getId());
                        contador++;
                    }
                } else {
                    Log.d(TAG, "Error getting documents: ", task.getException());
                }
            }
        });


    }

    private void rutinaBrazo(int dia) {//este parametro nos ayuda a poder usar este metodo cada vez que alguien tenga ejercicios de tiron en su rutina
        //se establece de base la dificultad media en los ejercicios de biceps ya que todos son aptos para todos
        db.collection("ejercicios").whereEqualTo("musculo", "biceps").whereEqualTo("dificultad", "media").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                int contador = 0;//esta variable la vamos a usar para establecer los ejercicios de un grupo muscular, si queremos 2 ejercicios en la rutina pues tendremos que recorrer 2 posciciones del task.result
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {//aunque este en un for solo da una vuelta porque solo devuelve un ejercicio pero a la hora de la lectura es necesario
                        //añadimos estas condiciones porque solo queremos 2 ejercicios de este musculo, por lo que solo guardamos el primer y segundo valor de la consulta
                        if (contador == 0)
                            DiasDeRutina.get(dia).setEjercicio1(document.getId());
                        else if (contador == 1)
                            DiasDeRutina.get(dia).setEjercicio2(document.getId());
                        contador++;
                    }
                } else {
                    Log.d(TAG, "Error getting documents: ", task.getException());
                }
            }
        });
        db.collection("ejercicios").whereEqualTo("musculo", "triceps").whereEqualTo("dificultad", dificultad).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                int contador = 0;
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        if (contador == 0)
                            DiasDeRutina.get(dia).setEjercicio3(document.getId());
                        else if (contador == 1)
                            DiasDeRutina.get(dia).setEjercicio4(document.getId());
                        contador++;
                    }
                } else {
                    Log.d(TAG, "Error getting documents: ", task.getException());
                }
            }
        });
        db.collection("ejercicios").whereEqualTo("musculo", "hombro").whereEqualTo("dificultad", dificultad).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                int contador = 0;
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        if (contador == 0)//hago esto porque solo quiero un ejercicio de hombro, y con el primer resultado me valdria
                            DiasDeRutina.get(dia).setEjercicio5(document.getId());
                    }
                } else {
                    Log.d(TAG, "Error getting documents: ", task.getException());
                }
            }
        });
    }

    private void rutinaPierna(int dia) {//este parametro nos ayuda a poder usar este metodo cada vez que alguien tenga ejercicios de pierna en su rutina

        //se establece de base la dificultad media en los ejercicios de pierna ya que todos son aptos para todos
        db.collection("ejercicios").whereEqualTo("musculo", "pierna").whereEqualTo("dificultad", "media").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                int contador = 0;//esta variable la vamos a usar para establecer los ejercicios de un grupo muscular, si queremos 2 ejercicios en la rutina pues tendremos que recorrer 2 posciciones del task.result
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {//aunque este en un for solo da una vuelta porque solo devuelve un ejercicio pero a la hora de la lectura es necesario
                        //añadimos estas condiciones porque solo queremos 2 ejercicios de este musculo, por lo que solo guardamos el primer y segundo valor de la consulta
                        if (contador == 0)
                            DiasDeRutina.get(dia).setEjercicio1(document.getId());
                        else if (contador == 1)
                            DiasDeRutina.get(dia).setEjercicio2(document.getId());
                        else if (contador == 2)
                            DiasDeRutina.get(dia).setEjercicio3(document.getId());
                        else if (contador == 3)
                            DiasDeRutina.get(dia).setEjercicio4(document.getId());
                        else if (contador == 4)
                            DiasDeRutina.get(dia).setEjercicio5(document.getId());

                        contador++;
                    }
                } else {
                    Log.d(TAG, "Error getting documents: ", task.getException());
                }

            }
        });


    }

    private void asignacionCardio() {
        if (cardio) {
            db.collection("ejercicios").whereEqualTo("musculo", "cardiovascular").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {//aunque este en un for solo da una vuelta porque solo devuelve un ejercicio y nos sirve para rellenar todos los dias de la rutina con cardio
                            for (Rutina r : DiasDeRutina) {
                                r.setEjercicioCardioVascular(document.getId());

                            }
                        }
                    } else {
                        Log.d(TAG, "Error getting documents: ", task.getException());
                    }
                }
            });
        } else {
            for (Rutina r : DiasDeRutina) {
                r.setEjercicioCardioVascular("inexistente");
            }
        }
    }

    private void rutinaEmpuje(int dia) {//este parametro nos ayuda a poder usar este metodo cada vez que alguien tenga ejercicios de empuje en su rutina


        db.collection("ejercicios").whereEqualTo("musculo", "pecho").whereEqualTo("dificultad", dificultad).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                int contador = 0;//esta variable la vamos a usar para establecer los ejercicios de un grupo muscular, si queremos 2 ejercicios en la rutina pues tendremos que recorrer 2 posciciones del task.result
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {//aunque este en un for solo da una vuelta porque solo devuelve un ejercicio pero a la hora de la lectura es necesario
                        //añadimos estas condiciones porque solo queremos 2 ejercicios de este musculo, por lo que solo guardamos el primer y segundo valor de la consulta

                        if (contador == 0) {
                            DiasDeRutina.get(dia).setEjercicio1(document.getId());
                        } else if (contador == 1) {
                            DiasDeRutina.get(dia).setEjercicio2(document.getId());
                        }
                        contador++;
                    }
                } else {
                    Log.d(TAG, "Error getting documents: ", task.getException());
                }
                System.out.println("soy primer ejercicio" + DiasDeRutina.get(dia).getEjercicio1());
                System.out.println("soy segun ejercicio" + DiasDeRutina.get(dia).getEjercicio2());
                task.isComplete();
            }

        });


        db.collection("ejercicios").whereEqualTo("musculo", "hombro").whereEqualTo("dificultad", dificultad).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                int contador = 0;
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        if (contador == 0)
                            DiasDeRutina.get(dia).setEjercicio3(document.getId());
                        else if (contador == 1)
                            DiasDeRutina.get(dia).setEjercicio4(document.getId());
                        contador++;
                    }
                } else {
                    Log.d(TAG, "Error getting documents: ", task.getException());
                }
            }
        });
        db.collection("ejercicios").whereEqualTo("musculo", "triceps").whereEqualTo("dificultad", dificultad).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                int contador = 0;
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        if (contador == 0)//hago esto porque solo quiero un ejercicio de triceps, y con el primer resultado me valdria
                            DiasDeRutina.get(dia).setEjercicio5(document.getId());
                    }
                } else {
                    Log.d(TAG, "Error getting documents: ", task.getException());
                }
            }
        });

    }


    public void onComplete(@NonNull Task<QuerySnapshot> task) {
        if (task.isSuccessful()) {
            for (QueryDocumentSnapshot document : task.getResult()) {
                document.getId();
            }
        } else {
            Log.d(TAG, "Error getting documents: ", task.getException());
        }
    }
}

