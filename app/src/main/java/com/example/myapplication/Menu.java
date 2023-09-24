package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;


public class Menu extends AppCompatActivity {

    Inicio inicio = new Inicio();
    Inventario inventario = new Inventario();
    Balance balance = new Balance();
    Deudas deudas =new Deudas();

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
            }
            return false;

        }

    };
    private void loadFragment(Fragment fragment){
        FragmentTransaction transaction =  getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container,fragment);
        transaction.commit();

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