package com.example.proyecto_app_gym;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class AuthActivity extends AppCompatActivity {

    EditText etRegEmail;
    EditText etRegPassword;
    Button btnReg;
    Button btnAcc;
    FirebaseAuth mAuth;
    private FirebaseFirestore db= FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        etRegEmail = findViewById(R.id.emaileditText);
        etRegPassword = findViewById(R.id.passeditText);
        btnReg = findViewById(R.id.signInbutton);
        mAuth = FirebaseAuth.getInstance();
        btnAcc=findViewById(R.id.loginbutton);

        btnReg.setOnClickListener(view ->{
            registrar();
        });
        btnAcc.setOnClickListener(view ->{
            acceder();
        });


    }
    private void registrar(){
        Intent i = new Intent(this, TomaDeDatos.class);
        String email = etRegEmail.getText().toString();
        String password = etRegPassword.getText().toString();
        if (TextUtils.isEmpty(email)){
            etRegEmail.setError("el email no puede estar vacio");
            etRegEmail.requestFocus();
        }else if (TextUtils.isEmpty(password)){
            etRegPassword.setError("la contraseña no puede estar vacia");
            etRegPassword.requestFocus();
        }else{
            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        i.putExtra("email", email);
                        Toast.makeText(AuthActivity.this, "Registro completado", Toast.LENGTH_SHORT).show();
                        startActivity(i);
                    }else{
                        Toast.makeText(AuthActivity.this, "Error en el registro " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
    private void acceder(){
        Intent i = new Intent(this, TomaDeDatos.class);
        String email = etRegEmail.getText().toString();
        String password = etRegPassword.getText().toString();
        if (TextUtils.isEmpty(email)){
            etRegEmail.setError("el email no puede estar vacio");
            etRegEmail.requestFocus();
        }else if (TextUtils.isEmpty(password)){
            etRegPassword.setError("la contraseña no puede estar vacia");
            etRegPassword.requestFocus();
        }else{
            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        i.putExtra("email", email);
                        Toast.makeText(AuthActivity.this, "usuario correcto", Toast.LENGTH_SHORT).show();
                        startActivity(i);
                    }else{
                        Toast.makeText(AuthActivity.this, "error en el registro " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }



}


