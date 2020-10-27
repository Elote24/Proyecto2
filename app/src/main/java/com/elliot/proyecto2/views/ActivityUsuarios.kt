package com.elliot.proyecto2.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.elliot.proyecto2.R
import com.elliot.proyecto2.adapters.ContentAdapter
import com.elliot.proyecto2.viewmodels.ActivityUsuariosViewModel


class ActivityUsuarios : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_usuarios)

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_usuarios)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val viewModel : ActivityUsuariosViewModel by viewModels()
        viewModel.getContents().observe(this, { contents ->
            val contentAdapter = ContentAdapter(contents)
            recyclerView.adapter = contentAdapter
            contentAdapter.notifyDataSetChanged()
        })
    }
}