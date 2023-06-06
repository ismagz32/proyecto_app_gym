package com.example.proyecto_app_gym;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class PlanDeEntreno extends AppCompatActivity {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    Spinner spinnerDias;
    TextView textViewCardioNombre, textViewEj1Nombre, textViewEj2Nombre, textViewEj3Nombre, textViewEj4Nombre, textViewEj5Nombre;
    TextView[] nombres = new TextView[6];
    Button buttonInfoCardio,buttonInfoEj1,buttonInfoEj2,buttonInfoEj3,buttonInfoEj4,buttonInfoEj5;

    Button buttonVideoCardio,buttonVideoEj1,buttonVideoEj2,buttonVideoEj3,buttonVideoEj4,buttonVideoEj5;
    String dificultadCardio,dificultad;
    String musculoCardio,musculo;
    String tiempoCardio,repeticiones;
    String url="https://youtube.com/shorts/EaR7aRC21jU";
    Button botonBuscar;
    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_de_entreno);
        botonBuscar = findViewById(R.id.buttonBuscar);
        spinnerDias = findViewById(R.id.spinnerDias);
        textViewCardioNombre = (TextView) findViewById(R.id.textViewCardioNombre);
        textViewEj1Nombre = (TextView) findViewById(R.id.textViewEj1Nombre);
        textViewEj2Nombre = (TextView) findViewById(R.id.textViewEj2Nombre);
        textViewEj3Nombre = (TextView) findViewById(R.id.textViewEj3Nombre);
        textViewEj4Nombre = (TextView) findViewById(R.id.textViewEj4Nombre);
        textViewEj5Nombre = (TextView) findViewById(R.id.textViewEj5Nombre);
        email = getIntent().getExtras().getString("email");
        buttonInfoCardio = findViewById(R.id.buttonInfoCardio);
        buttonInfoEj1 = findViewById(R.id.buttonInfoEj1);
        buttonInfoEj2 = findViewById(R.id.buttonInfoEj2);
        buttonInfoEj3 = findViewById(R.id.buttonInfoEj3);
        buttonInfoEj4 = findViewById(R.id.buttonInfoEj4);
        buttonInfoEj5 = findViewById(R.id.buttonInfoEj5);

        buttonVideoCardio=findViewById(R.id.buttonVideoCardio);
        buttonVideoEj1 = findViewById(R.id.buttonVideoEj1);
        buttonVideoEj2 = findViewById(R.id.buttonVideoEj2);
        buttonVideoEj3 = findViewById(R.id.buttonVideoEj3);
        buttonVideoEj4 = findViewById(R.id.buttonVideoEj4);
        buttonVideoEj5 = findViewById(R.id.buttonVideoEj5);

        String[] dias = {"lunes", "martes", "miercoles", "jueves", "viernes"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, dias);
        spinnerDias.setAdapter(adapter);

        botonBuscar.setOnClickListener(view -> {
            busqueda();
        });
    }

    public void enlaceVideoCardio(View view) {
        String ejercicio = (String) textViewCardioNombre.getText();

        DocumentReference docRef = db.collection("ejercicios").document(ejercicio);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {

                        url = document.getString("url");
                        Uri link = Uri.parse(url);
                        Intent i = new Intent(Intent.ACTION_VIEW, link);
                        startActivity(i);
                    } else {
                        System.out.println("no existen datos");
                    }
                } else {
                    System.out.println("error debido a  " + task.getException());
                }
            }
        });

    }

    public void enlaceVideoEj1(View view) {
        String ejercicio = (String) textViewEj1Nombre.getText();

        DocumentReference docRef = db.collection("ejercicios").document(ejercicio);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {

                        url = document.getString("url");
                        Uri link = Uri.parse(url);
                        Intent i = new Intent(Intent.ACTION_VIEW, link);
                        startActivity(i);
                    } else {
                        System.out.println("no existen datos");
                    }
                } else {
                    System.out.println("error debido a  " + task.getException());
                }
            }
        });



    }

    public void enlaceVideoEj2(View view) {
        String ejercicio = (String) textViewEj2Nombre.getText();

        DocumentReference docRef = db.collection("ejercicios").document(ejercicio);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {

                        url = document.getString("url");
                        Uri link = Uri.parse(url);
                        Intent i = new Intent(Intent.ACTION_VIEW, link);
                        startActivity(i);
                    } else {
                        System.out.println("no existen datos");
                    }
                } else {
                    System.out.println("error debido a  " + task.getException());
                }
            }
        });
    }

    public void enlaceVideoEj3(View view) {
        String ejercicio = (String) textViewEj3Nombre.getText();

        DocumentReference docRef = db.collection("ejercicios").document(ejercicio);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {

                        url = document.getString("url");
                        Uri link = Uri.parse(url);
                        Intent i = new Intent(Intent.ACTION_VIEW, link);
                        startActivity(i);
                    } else {
                        System.out.println("no existen datos");
                    }
                } else {
                    System.out.println("error debido a  " + task.getException());
                }
            }
        });
    }

    public void enlaceVideoEj4(View view) {
        String ejercicio = (String) textViewEj4Nombre.getText();

        DocumentReference docRef = db.collection("ejercicios").document(ejercicio);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {

                        url = document.getString("url");
                        Uri link = Uri.parse(url);
                        Intent i = new Intent(Intent.ACTION_VIEW, link);
                        startActivity(i);
                    } else {
                        System.out.println("no existen datos");
                    }
                } else {
                    System.out.println("error debido a  " + task.getException());
                }
            }
        });
    }

    public void enlaceVideoEj5(View view) {
        String ejercicio = (String) textViewEj5Nombre.getText();

        DocumentReference docRef = db.collection("ejercicios").document(ejercicio);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {

                        url = document.getString("url");
                        Uri link = Uri.parse(url);
                        Intent i = new Intent(Intent.ACTION_VIEW, link);
                        startActivity(i);
                    } else {
                        System.out.println("no existen datos");
                    }
                } else {
                    System.out.println("error debido a  " + task.getException());
                }
            }
        });
    }

    public void mostrarDatosCardio(View view) {
        String ejercicio = (String) textViewCardioNombre.getText();

        DocumentReference docRef = db.collection("ejercicios").document(ejercicio);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {

                        dificultadCardio = document.getString("dificultad");
                        musculoCardio = document.getString("musculo");
                        tiempoCardio = document.getLong("repeticiones").toString();
                    } else {
                        System.out.println("no existen datos");
                    }
                } else {
                    System.out.println("error debido a  " + task.getException());
                }
            }
        });
        view = LayoutInflater.from(this).inflate(R.layout.mostrardatos, null);

        TextView tvRepes = view.findViewById(R.id.edit_Repes);
        TextView tvDificultad = view.findViewById(R.id.edit_dificultad);
        TextView tvMusculo = view.findViewById(R.id.edit_musculo);

        tvRepes.setText(dificultadCardio + "(minutos)");
        tvDificultad.setText(tiempoCardio);
        tvMusculo.setText(musculoCardio);
        AlertDialog.Builder builder = new AlertDialog.Builder(this).setTitle("hola").setView(view).setPositiveButton("Salir", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //esto se hace para que se cierre el alert
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    public void mostrarDatosEj1(View view) {
        String ejercicio = (String) textViewEj1Nombre.getText();

        DocumentReference docRef = db.collection("ejercicios").document(ejercicio);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {

                        dificultad = document.getString("dificultad");
                        musculo = document.getString("musculo");
                        repeticiones = document.getLong("repeticiones").toString();
                    } else {
                        System.out.println("no existen datos");
                    }
                } else {
                    System.out.println("error debido a  " + task.getException());
                }
            }
        });
        view = LayoutInflater.from(this).inflate(R.layout.mostrardatos, null);

        TextView tvRepes = view.findViewById(R.id.edit_Repes);
        TextView tvDificultad = view.findViewById(R.id.edit_dificultad);
        TextView tvMusculo = view.findViewById(R.id.edit_musculo);

        tvRepes.setText(repeticiones);
        tvDificultad.setText(dificultad);
        tvMusculo.setText(musculo);
        AlertDialog.Builder builder = new AlertDialog.Builder(this).setTitle("hola").setView(view).setPositiveButton("Salir", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //esto se hace para que se cierre el alert
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    public void mostrarDatosEj2(View view) {
        String ejercicio = (String) textViewEj2Nombre.getText();

        DocumentReference docRef = db.collection("ejercicios").document(ejercicio);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {

                        dificultad = document.getString("dificultad");
                        musculo = document.getString("musculo");
                        repeticiones = document.getLong("repeticiones").toString();
                    } else {
                        System.out.println("no existen datos");
                    }
                } else {
                    System.out.println("error debido a  " + task.getException());
                }
            }
        });
        view = LayoutInflater.from(this).inflate(R.layout.mostrardatos, null);

        TextView tvRepes = view.findViewById(R.id.edit_Repes);
        TextView tvDificultad = view.findViewById(R.id.edit_dificultad);
        TextView tvMusculo = view.findViewById(R.id.edit_musculo);

        tvRepes.setText(repeticiones);
        tvDificultad.setText(dificultad);
        tvMusculo.setText(musculo);
        AlertDialog.Builder builder = new AlertDialog.Builder(this).setTitle("hola").setView(view).setPositiveButton("Salir", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //esto se hace para que se cierre el alert
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    public void mostrarDatosEj3(View view) {
        String ejercicio = (String) textViewEj3Nombre.getText();

        DocumentReference docRef = db.collection("ejercicios").document(ejercicio);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {

                        dificultad = document.getString("dificultad");
                        musculo = document.getString("musculo");
                        repeticiones = document.getLong("repeticiones").toString();
                    } else {
                        System.out.println("no existen datos");
                    }
                } else {
                    System.out.println("error debido a  " + task.getException());
                }
            }
        });
        view = LayoutInflater.from(this).inflate(R.layout.mostrardatos, null);

        TextView tvRepes = view.findViewById(R.id.edit_Repes);
        TextView tvDificultad = view.findViewById(R.id.edit_dificultad);
        TextView tvMusculo = view.findViewById(R.id.edit_musculo);

        tvRepes.setText(repeticiones);
        tvDificultad.setText(dificultad);
        tvMusculo.setText(musculo);
        AlertDialog.Builder builder = new AlertDialog.Builder(this).setTitle("hola").setView(view).setPositiveButton("Salir", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //esto se hace para que se cierre el alert
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    public void mostrarDatosEj4(View view) {
        String ejercicio = (String) textViewEj4Nombre.getText();

        DocumentReference docRef = db.collection("ejercicios").document(ejercicio);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {

                        dificultad = document.getString("dificultad");
                        musculo= document.getString("musculo");
                        repeticiones = document.getLong("repeticiones").toString();

                    } else {
                        System.out.println("no existen datos");
                    }
                } else {
                    System.out.println("error debido a  " + task.getException());
                }
            }
        });
        view = LayoutInflater.from(this).inflate(R.layout.mostrardatos, null);

        TextView tvRepes = view.findViewById(R.id.edit_Repes);
        TextView tvDificultad = view.findViewById(R.id.edit_dificultad);
        TextView tvMusculo = view.findViewById(R.id.edit_musculo);

        tvRepes.setText(repeticiones);
        tvDificultad.setText(dificultad);
        tvMusculo.setText(musculo);
        AlertDialog.Builder builder = new AlertDialog.Builder(this).setTitle("Descripcion").setView(view).setPositiveButton("Salir", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //esto se hace para que se cierre el alert
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    public void mostrarDatosEj5(View view) {
        String ejercicio = (String) textViewEj5Nombre.getText();

        DocumentReference docRef = db.collection("ejercicios").document(ejercicio);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {

                        dificultad = document.getString("dificultad");
                        musculo = document.getString("musculo");
                        repeticiones = document.getLong("repeticiones").toString();
                    } else {
                        System.out.println("no existen datos");
                    }
                } else {
                    System.out.println("error debido a  " + task.getException());
                }
            }
        });
        view = LayoutInflater.from(this).inflate(R.layout.mostrardatos, null);

        TextView tvRepes = view.findViewById(R.id.edit_Repes);
        TextView tvDificultad = view.findViewById(R.id.edit_dificultad);
        TextView tvMusculo = view.findViewById(R.id.edit_musculo);

        tvRepes.setText(repeticiones);
        tvDificultad.setText(dificultad);
        tvMusculo.setText(musculo);
        AlertDialog.Builder builder = new AlertDialog.Builder(this).setTitle("hola").setView(view).setPositiveButton("Salir", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //esto se hace para que se cierre el alert
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    private void busqueda() {
        db.collection("Rutinas").whereEqualTo("dia", spinnerDias.getSelectedItem().toString()).whereEqualTo("email", email).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                boolean existe=false;
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        textViewCardioNombre.setText((String) document.get("Cardio"));
                        textViewEj1Nombre.setText((String) document.get("Ejercicio1"));
                        textViewEj2Nombre.setText((String) document.get("Ejercicio2"));
                        textViewEj3Nombre.setText((String) document.get("Ejercicio3"));
                        textViewEj4Nombre.setText((String) document.get("Ejercicio4"));
                        textViewEj5Nombre.setText((String) document.get("Ejercicio5"));

                        buttonInfoCardio.setEnabled(true);
                        buttonInfoEj1.setEnabled(true);
                        buttonInfoEj2.setEnabled(true);
                        buttonInfoEj3.setEnabled(true);
                        buttonInfoEj4.setEnabled(true);
                        buttonInfoEj5.setEnabled(true);

                        buttonVideoCardio.setEnabled(true);
                        buttonVideoEj1.setEnabled(true);
                        buttonVideoEj2.setEnabled(true);
                        buttonVideoEj3.setEnabled(true);
                        buttonVideoEj4.setEnabled(true);
                        buttonVideoEj5.setEnabled(true);
                        existe=true;
                    }
                    if (!existe){
                        textViewCardioNombre.setText("Descanso");
                        textViewEj1Nombre.setText("Descanso");
                        textViewEj2Nombre.setText("Descanso");
                        textViewEj3Nombre.setText("Descanso");
                        textViewEj4Nombre.setText("Descanso");
                        textViewEj5Nombre.setText("Descanso");

                        buttonInfoCardio.setEnabled(false);
                        buttonInfoEj1.setEnabled(false);
                        buttonInfoEj2.setEnabled(false);
                        buttonInfoEj3.setEnabled(false);
                        buttonInfoEj4.setEnabled(false);
                        buttonInfoEj5.setEnabled(false);

                        buttonVideoCardio.setEnabled(false);
                        buttonVideoEj1.setEnabled(false);
                        buttonVideoEj2.setEnabled(false);
                        buttonVideoEj3.setEnabled(false);
                        buttonVideoEj4.setEnabled(false);
                        buttonVideoEj5.setEnabled(false);

                    }
                } else {

                    Log.d(TAG, "Error getting documents: ", task.getException());
                }
            }
        });
    }


}