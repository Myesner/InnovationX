package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Registro extends AppCompatActivity {

    EditText et_email,et_pass,et_confpass,et_nombre;
    Button btn_registrar,btn_login;

    private String email;
    private String pass;
    private String nombre;

    FirebaseAuth mAuth;

     DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        et_nombre = findViewById(R.id.et_nombre);
        et_email = findViewById(R.id.et_ResetEmail);
        et_pass = findViewById(R.id.et_ResetContra);
        et_confpass = findViewById(R.id.et_ResetRC);

        mAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();

        btn_registrar = findViewById(R.id.btn_CrearC);
        btn_registrar.setOnClickListener(view -> registrarUsuario());

        btn_login = findViewById(R.id.btn_IniciarS);
        btn_login.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(),Login.class));
            finish();
        });
    }


    public void registrarUsuario(){
        nombre = et_nombre.getText().toString().trim();
        email = et_email.getText().toString().trim();
        pass = et_pass.getText().toString().trim();
        String confipass = et_confpass.getText().toString().trim();

        if (nombre.isEmpty() || email.isEmpty() || pass.isEmpty() || confipass.isEmpty()) {
            mostrarMensaje("Por favor, ingrese todos los valores.");
        } else if (!emailValido(email)) {
            et_email.setError("Ingrese un Email válido.");
        } else if (!pass.equals(confipass)) {
            et_confpass.setError("Las contraseñas no coinciden.");
        } else if (pass.length() <= 6) {
            et_pass.setError("La contraseña debe ser mayor a 6 caracteres.");
        } else {
            mAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        String userId = mAuth.getCurrentUser().getUid();
                        // Crear un objeto para almacenar en la base de datos
                        Usuario usuario = new Usuario(nombre, email, pass);
                        // Guardar el objeto en la base de datos bajo el UID del usuario
                        databaseReference.child("Usuarios").child(userId).setValue(usuario);

                        mostrarMensaje("Cuenta creada correctamente.");
                        irInicio();
                    } else {
                        mostrarMensaje("La cuenta ya existe o ha ocurrido un error.");
                    }
                }
            });
        }
    }

    private void mostrarMensaje(String mensaje) {
        Toast.makeText(Registro.this, mensaje, Toast.LENGTH_SHORT).show();
    }


    private void irInicio() {
        startActivity(new Intent(getApplicationContext(),inicio.class));
        finish();
    }

    public  boolean emailValido(String email){
        String expresion = "[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expresion, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return  matcher.matches();
    }
}