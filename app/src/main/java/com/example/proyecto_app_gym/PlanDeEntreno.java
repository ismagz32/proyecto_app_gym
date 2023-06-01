package com.example.proyecto_app_gym;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class PlanDeEntreno extends AppCompatActivity {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    Spinner spinnerDias;
    TextView textViewCardioNombre, textViewEj1Nombre, textViewEj2Nombre, textViewEj3Nombre, textViewEj4Nombre, textViewEj5Nombre;
    TextView[] nombres =new TextView[6];

    Button buttonCardio, buttonEj1Repes, buttonEj2Repes, buttonE31Repes, buttonEj4Repes, buttonEj5Repes;

    Button botonBuscar, BotonVideo1, BotonVideo2, BotonVideo3, BotonVideo4, BotonVideo5, BotonVideo6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_de_entreno);
        botonBuscar=findViewById(R.id.buttonBuscar);
        spinnerDias=findViewById(R.id.spinnerDias);
        textViewCardioNombre = (TextView) findViewById(R.id.textViewCardioNombre);
        textViewEj1Nombre = (TextView) findViewById(R.id.textViewEj1Nombre);
        textViewEj2Nombre = (TextView) findViewById(R.id.textViewEj2Nombre);
        textViewEj3Nombre = (TextView) findViewById(R.id.textViewEj3Nombre);
        textViewEj4Nombre = (TextView) findViewById(R.id.textViewEj4Nombre);
        textViewEj5Nombre = (TextView) findViewById(R.id.textViewEj5Nombre);
        /*
        buttonCardioTiempo=findViewById(R.id.);
        buttonEj1Repes;
        buttonEj2Repes;
        buttonE31Repes;
        buttonEj4Repes;
        buttonEj5Repes;*/

        String[] dias = {"lunes", "martes", "miercoles", "jueves", "viernes"};
        TextView[] nombres =new TextView[6];
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, dias);
        spinnerDias.setAdapter(adapter);

        botonBuscar.setOnClickListener(view -> {
            busqueda();
        });
    }

    public void mostrarDatos(View view){
        view= LayoutInflater.from(this).inflate(R.layout.mostrardatos,null);
        TextView repes=view.findViewById(R.id.edit_Repes);
        AlertDialog.Builder builder=new AlertDialog.Builder(this).setTitle("hola").setView(view).setPositiveButton("Salir", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //esto se hace para que se cierre el alert
            }
        });
        AlertDialog alert=builder.create();
        alert.show();
    }
    private void busqueda() {
        db.collection("Rutinas").whereEqualTo("dia", "lunes").whereEqualTo("email", "isma@gmail.com").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                int contador = 0;//esta variable la vamos a usar para establecer los ejercicios de un grupo muscular, si queremos 2 ejercicios en la rutina pues tendremos que recorrer 2 posciciones del task.result
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {//aunque este en un for solo da una vuelta porque solo devuelve un ejercicio pero a la hora de la lectura es necesario
                        //añadimos estas condiciones porque solo queremos 2 ejercicios de este musculo, por lo que solo guardamos el primer y segundo valor de la consulta
                        textViewCardioNombre.setText((String)document.get("Cardio"));
                        textViewEj1Nombre.setText((String)document.get("Ejercicio1"));
                        textViewEj2Nombre.setText((String)document.get("Ejercicio2"));
                        textViewEj3Nombre.setText((String)document.get("Ejercicio3"));
                        textViewEj4Nombre.setText((String)document.get("Ejercicio4"));
                        textViewEj5Nombre.setText((String)document.get("Ejercicio5"));

                    }
                } else {
                    Log.d(TAG, "Error getting documents: ", task.getException());
                }
            }
        });
    }


}