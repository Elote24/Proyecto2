package com.elliot.proyecto2.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.elliot.proyecto2.R
import com.elliot.proyecto2.models.entities.Content

class ContentAdapter(private val contents: List<Content>) : RecyclerView.Adapter<ContentAdapter.ContentAdapterViewHolder>() {

   override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentAdapterViewHolder {
      val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.usuario, parent, false)

       return ContentAdapterViewHolder(view)
    }

    override fun onBindViewHolder(holder: ContentAdapterViewHolder, position: Int) {
        val content = contents[position]
        holder.onBind(content)
    }

    override fun getItemCount(): Int {
        return contents.size
    }

    inner class ContentAdapterViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun onBind(content: Content) {
            val textViewNombre = view.findViewById<TextView>(R.id.textNombre)
            val textViewTelefono = view.findViewById<TextView>(R.id.textTelefono)
            val textViewCorreo = view.findViewById<TextView>(R.id.textCorreo)
            val textViewPlan = view.findViewById<TextView>(R.id.textPlan)
            val textViewCategorias = view.findViewById<TextView>(R.id.textCategoria)

            textViewNombre.text = content.nombre
            textViewTelefono.text = content.telefono
            textViewCorreo.text = content.telefono
            textViewPlan.text = content.plan
            textViewCategorias.text = content.categorias


        }
    }
}