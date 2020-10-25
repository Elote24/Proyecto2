package com.elliot.proyecto2.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.elliot.proyecto2.R
import com.elliot.proyecto2.models.entities.Categoria

class CategoriaAdapter(private val categorias: List<Categoria>) :
    RecyclerView.Adapter<CategoriaAdapter.CategoriaHolder>() {
    class CategoriaHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun onBind(categoria: Categoria) {
            val title = view.findViewById<TextView>(R.id.title)
            val image = view.findViewById<ImageView>(R.id.image)

            image.setImageResource(categoria.image)
            title.text = categoria.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriaHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.card_category, parent, false)
        return CategoriaHolder(view)
    }

    override fun onBindViewHolder(categoriaHolder: CategoriaHolder, position: Int) {
        val categoria = categorias[position]
        categoriaHolder.onBind(categoria)
    }

    override fun getItemCount(): Int {
        return categorias.size
    }
}