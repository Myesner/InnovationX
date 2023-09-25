package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ProductoAdapter extends RecyclerView.Adapter<ProductoAdapter.ViewHolder>{
    private List<Producto> productos;

    public ProductoAdapter(List<Producto> productos) {
        this.productos = productos;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_item_producto, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Producto producto = productos.get(position);
        holder.bind(producto);
    }

    @Override
    public int getItemCount() {
        return productos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtNombre;
        private TextView txtCostoUnitario;
        private TextView txtPrecioUnitario;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNombre = itemView.findViewById(R.id.txtNombre);
            txtCostoUnitario = itemView.findViewById(R.id.txtCostoUnitario);
            txtPrecioUnitario = itemView.findViewById(R.id.txtPrecioUnitario);
        }

        public void bind(Producto producto) {
            txtNombre.setText(producto.getNombre());
            txtCostoUnitario.setText(producto.getCostoUnitario());
            txtPrecioUnitario.setText(producto.getPrecioUnitario());
        }
    }
}
