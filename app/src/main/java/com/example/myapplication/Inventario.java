package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Inventario#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Inventario extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private RecyclerView recyclerView;
    private ProductoAdapter productoAdapter;
    private List<Producto> listaProductos;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Inventario() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Inventario.
     */
    // TODO: Rename and change types and number of parameters
    public static Inventario newInstance(String param1, String param2) {
        Inventario fragment = new Inventario();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        if (getArguments() != null) {
            Producto producto = (Producto) getArguments().getSerializable("producto");

        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.activity_inventario, container, false);

        Button btnAgregar = rootView.findViewById(R.id.btnAgregar);
        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               startActivity(new Intent(getActivity(),Nuevo_P.class));
            }
        });

        recyclerView = rootView.findViewById(R.id.recyclerViewProductos);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        listaProductos = obtenerListaDeProductos(); // Aquí debes obtener la lista de productos
        productoAdapter = new ProductoAdapter(listaProductos);
        recyclerView.setAdapter(productoAdapter);


        return rootView;

    }

    // Método para obtener la lista de productos (puedes cargarla desde donde corresponda)
    private List<Producto> obtenerListaDeProductos() {
        List<Producto> lista = new ArrayList<>();
        // Agrega tus productos a la lista
        return lista;
    }

    // Método estático para crear una nueva instancia del fragmento con argumentos
    public static Inventario newInstance(Producto producto) {
        Inventario fragment = new Inventario();
        Bundle args = new Bundle();
        args.putSerializable("producto", producto);
        fragment.setArguments(args);
        return fragment;
    }

}