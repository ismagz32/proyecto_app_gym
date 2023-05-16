package com.example.proyecto_app_gym;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

public class AuthActivity extends AppCompatActivity {

    EditText etRegEmail;
    EditText etRegPassword;
    Button btnReg;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        etRegEmail = findViewById(R.id.emaileditText);
        etRegPassword = findViewById(R.id.passeditText);
        btnReg = findViewById(R.id.signInbutton);
        mAuth = FirebaseAuth.getInstance();

        btnReg.setOnClickListener(view ->{
            registrar();
        });


    }
    private void registrar(){
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
                        Toast.makeText(AuthActivity.this, "Registro completado", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(AuthActivity.this, "Error en el registro " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }



}


