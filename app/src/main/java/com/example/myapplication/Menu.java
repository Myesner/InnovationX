package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;


public class Menu extends AppCompatActivity {

    Inicio inicio = new Inicio();
    Inventario inventario = new Inventario();
    Balance balance = new Balance();
    Deudas deudas =new Deudas();
    Ventas ventas = new Ventas();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        BottomNavigationView navigation = findViewById(R.id.bottom_navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        loadFragment(inicio);


    }

    private final BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            int itemId = item.getItemId();
            if (itemId == R.id.primerFragmento) {
                loadFragment(inicio);
                return true;
            } else if (itemId == R.id.segundoFragmento) {
                loadFragment(inventario);
                return true;
            } else if (itemId == R.id.tercerFragmento) {
                loadFragment(balance);
                return true;
            } else if (itemId == R.id.cuartoFragmento) {
                loadFragment(deudas);
                return true;
            }else if (itemId == R.id.quintoFragmento) {
                loadFragment(ventas);
                return true;
            }
            return false;

        }

    };
    private void loadFragment(Fragment fragment){
        FrameLayout frameContainer = findViewById(R.id.frame_container);
        if (frameContainer != null) {
            // La vista frame_container está disponible y se puede utilizar.
            // Ahora puedes proceder a reemplazar el fragmento.
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frame_container, fragment) // Reemplaza R.id.frame_container
                    .commit();
        } else {
            // frame_container es nulo, por lo que no puedes continuar.
            // Realiza alguna acción de manejo de errores o notifica al usuario.
        }

    }

    public void cerrarSesion() {
        // Cerrar la sesión del usuario
        FirebaseAuth.getInstance().signOut();

        // Redirigir al usuario a la pantalla de inicio de sesión (Login)
        Intent intent = new Intent(getApplicationContext(), Login.class);
        startActivity(intent);

        // Finalizar la actividad actual
        finish();
    }
}