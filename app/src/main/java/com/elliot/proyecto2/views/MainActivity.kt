package com.elliot.proyecto2.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.elliot.proyecto2.R
import com.elliot.proyecto2.adapters.CategoriaAdapter
import com.elliot.proyecto2.models.entities.Categoria
import com.elliot.proyecto2.models.entities.Content
import com.elliot.proyecto2.viewmodels.MainActivityViewModel
import com.google.android.material.button.MaterialButton
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var textNombre: TextView
    private lateinit var textTelefono: TextView
    private lateinit var textCorreo: TextView
    private lateinit var textPlan: TextView
    private lateinit var textCategoria: TextView
    private lateinit var editNombre: EditText
    private lateinit var editTelefono: EditText
    private lateinit var editCorreo: EditText
    private lateinit var checkRomantico: CheckBox
    private lateinit var checkTerror: CheckBox
    private lateinit var checkComedia: CheckBox
    private lateinit var checkAnime: CheckBox
    private lateinit var checkAccion: CheckBox
    private lateinit var radioBasico: RadioButton
    private lateinit var radioEstandar: RadioButton
    private lateinit var radioPremium: RadioButton

    private val MainActivityViewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fabver = findViewById<FloatingActionButton>(R.id.fabActionDialogUsuarios)

        fabver.setOnClickListener {
            val intent = Intent(this, ActivityUsuarios::class.java)
            startActivity(intent)
        }

        checkRomantico = findViewById<CheckBox>(R.id.checkRomantica)
        checkTerror = findViewById<CheckBox>(R.id.checkTerror)
        checkComedia = findViewById<CheckBox>(R.id.checkComedia)
        checkAnime = findViewById<CheckBox>(R.id.checkAnime)
        checkAccion = findViewById<CheckBox>(R.id.checkAccion)

        textNombre = findViewById<TextView>(R.id.textNombre)
        textTelefono = findViewById<TextView>(R.id.textTelefono)
        textCorreo = findViewById<TextView>(R.id.textCorreo)
        textPlan = findViewById<TextView>(R.id.textPlan)
        textCategoria = findViewById<TextView>(R.id.textCategoria)

        editNombre = findViewById<EditText>(R.id.editNombre)
        editTelefono = findViewById<EditText>(R.id.editTelefono)
        editCorreo = findViewById<EditText>(R.id.editCorreo)

        radioBasico = findViewById<RadioButton>(R.id.radioBasico)
        radioEstandar = findViewById<RadioButton>(R.id.radioEstandar)
        radioPremium = findViewById<RadioButton>(R.id.radioPremium)

        checkRomantico.setOnCheckedChangeListener(changeChecked)
        checkTerror.setOnCheckedChangeListener(changeChecked)
        checkComedia.setOnCheckedChangeListener(changeChecked)
        checkAnime.setOnCheckedChangeListener(changeChecked)
        checkAccion.setOnCheckedChangeListener(changeChecked)


        val  buttonSave = findViewById<MaterialButton>(R.id.button_save)
        buttonSave.setOnClickListener {
            var textoRadio = ""
            if (radioBasico.isChecked) {
                textoRadio = radioBasico.text.toString()
            } else if (radioEstandar.isChecked) {
                textoRadio = radioEstandar.text.toString()
            } else if (radioPremium.isChecked) {
                textoRadio = radioBasico.text.toString()
            }
            var textoCategoria = ""
            if (checkRomantico.isChecked) {
                textoCategoria += checkRomantico.text.toString() + "\n"
            }
            if (checkTerror.isChecked) {
                textoCategoria += checkTerror.text.toString() + "\n"
            }
            if (checkComedia.isChecked) {
                textoCategoria += checkComedia.text.toString() + "\n"
            }
            if (checkAnime.isChecked) {
                textoCategoria += checkAnime.text.toString() + "\n"
            }
            if (checkAccion.isChecked) {
                textoCategoria += checkAccion.text.toString() + "\n"
            }
            val nombre = editNombre.text.toString()
            val telefono = editTelefono.text.toString()
            val correo = editCorreo.text.toString()
            val plan = textoRadio
            val categoria = textoCategoria

            val content = Content(
                nombre = nombre,
                telefono = telefono,
                correo = correo,
                plan = plan,
                categorias = categoria
            )

            if (valida()!=""){
                val alertDialog = AlertDialog.Builder(this)
                    .setTitle("LLene toda la información")
                    .setMessage(
                        valida()
                    )
                    .create()

                alertDialog.show()
                return@setOnClickListener
            }

            MainActivityViewModel.insertContent(content)
        }

            MainActivityViewModel.notifyInsertContent().observe(this,{ succesful ->
                    if (succesful){
                        Toast.makeText(this,"Guardado exitoso", Toast.LENGTH_LONG).show()
                        limpiar()
                    }else{
                        Toast.makeText(this,"No se pudo guardar", Toast.LENGTH_LONG).show()
                    }
                })
    }

    fun  limpiar(){
        radioBasico.isChecked
        checkAccion.isChecked=false
        checkComedia.isChecked=false
        checkRomantico.isChecked=false
        checkAnime.isChecked=false
        checkTerror.isChecked=false
        editNombre.setText("")
        editTelefono.setText("")
        editCorreo.setText("")
    }

    fun  valida(): String {
        var texto=""
        if(editNombre.text.toString()==""){
            texto+="Debe ingresar el Nombre \n"
        }
        if(editTelefono.text.toString()==""){
            texto+="Debe ingresar el Telefono \n "
        }
        if(editCorreo.text.toString()==""){
            texto+="Debe ingresar el Correo \n"
        }
        if(radioBasico.isChecked==false &&  radioEstandar.isChecked==false && radioPremium.isChecked==false){
            texto+="Debe seleccionar un plan \n"
        }
        if(checkAccion.isChecked==false &&  checkAnime.isChecked==false && checkComedia.isChecked==false
            && checkRomantico.isChecked==false && checkTerror.isChecked==false ){
            texto+="Debe seleccionar una categoria minimo \n"
        }
        return texto
    }






    private val changeChecked = CompoundButton.OnCheckedChangeListener { button, checked ->
        val categorias = mutableListOf<Categoria>()
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        val categoriaAdapter = CategoriaAdapter(categorias)
        recyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.adapter = categoriaAdapter

        if (checkRomantico.isChecked) {
            categorias.add(
                Categoria(
                    " Romantica",
                    R.drawable.ic_romantico
                )
            )
            categoriaAdapter.notifyDataSetChanged()
        }

        if (checkTerror.isChecked) {
            categorias.add(
                Categoria(
                    " Terror",
                    R.drawable.ic_terror
                )
            )
            categoriaAdapter.notifyDataSetChanged()
        }

        if (checkComedia.isChecked) {
            categorias.add(
                Categoria(
                    " Comedia",
                    R.drawable.ic_comedia
                )
            )
            categoriaAdapter.notifyDataSetChanged()
        }

        if (checkAnime.isChecked) {
            categorias.add(
                Categoria(
                    " Anime",
                    R.drawable.ic_anime
                )
            )
            categoriaAdapter.notifyDataSetChanged()
        }

        if (checkAccion.isChecked) {
            categorias.add(
                Categoria(
                    " Accion",
                    R.drawable.ic_accion
                )
            )
            categoriaAdapter.notifyDataSetChanged()
        }
    }
}