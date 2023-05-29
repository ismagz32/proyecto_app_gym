package com.example.proyecto_app_gym;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class TomaDeDatos extends AppCompatActivity {

    private FirebaseFirestore db= FirebaseFirestore.getInstance();
    Button btnSave;
    String email;
    EditText etAltura,etPeso,etActividad,etEdad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toma_de_datos);
        TextView texto =(TextView) findViewById(R.id.textViewemail);
        email = getIntent().getExtras().getString("email");
        texto.setText(texto.getText()+" "+email);

        etEdad = findViewById(R.id.editTextEdad);
        etPeso = findViewById(R.id.editTextPeso);
        etActividad = findViewById(R.id.editTextActividad);
        etAltura = findViewById(R.id.editTextAltura);


        btnSave=findViewById(R.id.buttonSave);

        btnSave.setOnClickListener(view ->{
            guardar();
        });
    }

    private void guardar(){
        if (TextUtils.isEmpty(etEdad.getText())){
            etEdad.setError("el campo es obligatorio");
            etEdad.requestFocus();
        }else if (TextUtils.isEmpty(etPeso.getText())) {
            etPeso.setError("el campo es obligatorio");
            etPeso.requestFocus();
        }else if (TextUtils.isEmpty(etAltura.getText())){
            etAltura.setError("el campo es obligatorio");
            etAltura.requestFocus();
        }
        else if (TextUtils.isEmpty(etActividad.getText())){
            etActividad.setError("el campo es obligatorio");
            etActividad.requestFocus();
        }else {


            int edad = Integer.parseInt(etEdad.getText().toString().trim());
            int altura = Integer.parseInt(etAltura.getText().toString().trim());
            int peso = Integer.parseInt(etPeso.getText().toString().trim());
            int actividad = Integer.parseInt(etActividad.getText().toString().trim());

            HashMap<String, Integer> datos = new HashMap<>();
            datos.put("peso", peso);
            datos.put("altura", altura);
            datos.put("edad", edad);
            datos.put("actividadFisica", actividad);
            db.collection("users").document(email).set(datos);
            /*
            DocumentReference docRef = db.collection("ejercicios").document("PressBanca");
            docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                @Override
                public void onSuccess(DocumentSnapshot documentSnapshot) {
                    Ejercicio ej = documentSnapshot.toObject(Ejercicio.class);

                }
            });
            */


        }


    }
}

