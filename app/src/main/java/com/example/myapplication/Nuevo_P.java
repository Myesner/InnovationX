package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;


public class Nuevo_P extends AppCompatActivity {

    EditText etNombre,etPrecioU,etCostoU;
    Button btnguardar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_p);

        etNombre = findViewById(R.id.etNombre);
        etPrecioU = findViewById(R.id.etPrecioU);
        etCostoU = findViewById(R.id.etCostoU);
        btnguardar = findViewById(R.id.btnGuardar);


        btnguardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Guardar un valor
                String nombre = etNombre.getText().toString().trim();
                String PrecioU = etPrecioU.getText().toString().trim();
                String CostoU = etCostoU.getText().toString().trim();

                Producto producto = new Producto(nombre,CostoU,PrecioU);
                Inventario fragment = Inventario.newInstance(producto);

                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frame_container, fragment) // Reemplaza R.id.contenedor_fragmento con el ID de tu contenedor de fragmento
                        .commit();

            }
        });



    }

}