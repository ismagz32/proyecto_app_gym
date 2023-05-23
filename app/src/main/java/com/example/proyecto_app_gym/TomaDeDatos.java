package com.example.proyecto_app_gym;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class TomaDeDatos extends AppCompatActivity {

    private FirebaseFirestore db= FirebaseFirestore.getInstance();
    Button btnSave;
    String email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toma_de_datos);
        TextView texto =(TextView) findViewById(R.id.textViewemail);
        email = getIntent().getExtras().getString("email");
        texto.setText(email);

        btnSave=findViewById(R.id.buttonSave);

        btnSave.setOnClickListener(view ->{
            guardar();
        });
    }

    private void guardar(){
        HashMap<String, Integer> datos= new HashMap<>();
        datos.put("Yellow", 1);
        datos.put("Black", 2);
        datos.put("Green", 3);
        db.collection("users").document(email).set(datos);
    }
}

