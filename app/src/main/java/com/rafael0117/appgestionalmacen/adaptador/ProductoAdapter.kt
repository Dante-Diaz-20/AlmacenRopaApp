package com.rafael0117.appgestionalmacen.adaptador

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rafael0117.appgestionalmacen.DetalleProducto
import com.rafael0117.appgestionalmacen.EditarProducto
import com.rafael0117.appgestionalmacen.R
import com.rafael0117.appgestionalmacen.entidad.Producto
import com.rafael0117.appgestionalmacen.holder.VistaProducto

class ProductoAdapter (var lista:ArrayList<Producto>,
private val onItemClick: (Producto) -> Unit
):RecyclerView.Adapter<VistaProducto>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VistaProducto {
        val item = LayoutInflater.from(parent.context).inflate(R.layout.item_producto, parent, false)
        return VistaProducto(item)
    }

    override fun getItemCount(): Int {
        return lista.size
    }

    override fun onBindViewHolder(holder: VistaProducto, position: Int) {
        val producto = lista[position]
        holder.tvCodigo.text = producto.codigo.toString()
        holder.tvNombre.text = producto.nombre
        holder.tvDescripcion.text = producto.descripcion
        holder.tvEstado.text = if (producto.estado == 1) "Activo" else "Desactivado"
        holder.tvEstado.setTextColor(
            ContextCompat.getColor(
                holder.itemView.context,
                if (producto.estado == 1) android.R.color.holo_green_dark else android.R.color.holo_red_dark
            )
        )
        Glide.with(holder.itemView.context)
            .load(producto.imagen) // puede ser una URL o una ruta local
            .placeholder(R.drawable.baseline_broken_image_24) // opcional
            .error(R.drawable.baseline_image_24) // opcional
            .into(holder.imgProducto)

        holder.btnVerMas.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, DetalleProducto::class.java).apply {
                putExtra("codigo", producto.codigo)
                putExtra("nombre", producto.nombre)
                putExtra("descripcion", producto.descripcion)
                putExtra("categoria", producto.categoria)
                putExtra("categoriaNombre", producto.categoriaNombre)
                putExtra("marca", producto.marca)
                putExtra("modelo", producto.modelo)
                putExtra("proveedorNombre", producto.proveedorNombre)
                putExtra("preciocompra", producto.preciocompra)
                putExtra("precioventa", producto.precioventa)
                putExtra("stockActual", producto.stockActual)
                putExtra("stockMinimo", producto.stockMinimo)
                putExtra("ubicacion", producto.ubicacion)
                putExtra("fechaIngreso", producto.fechaIngreso)
                putExtra("estado", producto.estado)
                putExtra("imagen", producto.imagen)
            }
            context.startActivity(intent)
        }

        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, EditarProducto::class.java).apply {
                putExtra("producto", producto)
            }
            context.startActivity(intent)
        }


    }
}