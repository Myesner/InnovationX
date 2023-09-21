package com.example.myapplication;

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
import com.google.firebase.auth.FirebaseAuth;

public class resetPass extends AppCompatActivity {

    EditText et_correoreset;
    FirebaseAuth auth;
    Button btn_ResetC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_pass);

        et_correoreset = findViewById(R.id.et_ResetEmail);
        btn_ResetC = findViewById(R.id.btn_ResetC);
        auth = FirebaseAuth.getInstance();

       btn_ResetC.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               recuperar();
           }
       });


    }

    public void recuperar(){
        auth.setLanguageCode("es");

        String email = et_correoreset.getText().toString().trim();

        if (TextUtils.isEmpty(email)){
            auth.setLanguageCode("es");
            Toast.makeText(resetPass.this, "ingrese correo", Toast.LENGTH_SHORT).show();
            return;
        }

        auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    auth.setLanguageCode("es");
                    startActivity(new Intent(getApplicationContext(), Login.class));
                    finish();
                    Toast.makeText(resetPass.this, "te enviamos enlase en tu correo", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(resetPass.this, "Correo no esta registrado", Toast.LENGTH_SHORT).show();
                }

            }
        });



    }


}