package pe.paku.mvvmkotlin.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.launch
import pe.paku.mvvmkotlin.R
import pe.paku.mvvmkotlin.beans.LibroBean
import pe.paku.mvvmkotlin.viewmodels.LibroViewModel

class MainActivity : AppCompatActivity() {

    //1 - Colocamos nuestras referencias de viewModel
    private lateinit var viewModelLibro : LibroViewModel
    private lateinit var libroAdapter : LibroAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Iniciamos las vistas
        setupViewModel()
        setupAdapter()
    }

    private fun setupViewModel(){

        //Suscribir el viewmodel
        viewModelLibro = ViewModelProvider(this).get(LibroViewModel::class.java)

        //viewModelLibro.libros.observe(this, Observer {  })
        viewModelLibro.libros.observe(this, renderLibros)
        viewModelLibro.isViewLoading.observe(this, loadingTemplate)
        viewModelLibro.isEmptylist.observe(this, emptyListLibros)
        viewModelLibro.onMessageError.observe(this, mensajeError)

    }

    private val renderLibros = Observer<List<LibroBean>> {listaLibros ->

        container_error.visibility = View.GONE
        rv_libros.visibility = View.VISIBLE

        //Actualizamos el adapter
        libroAdapter.updateLibros(listaLibros)


    }

    private val loadingTemplate = Observer<Boolean>{
        val visibilitShimmer = if (it)View.VISIBLE else View.GONE
        shimmer_loading.visibility = visibilitShimmer
    }

    private val mensajeError = Observer<Any>{

        container_error.visibility = View.VISIBLE
        tv_error_message.visibility = View.VISIBLE
        tv_error_message.text = "Error al obtener los libros, vuelve a intentarlo m{as tarde"
    }

    private val emptyListLibros = Observer<Boolean> {

        container_error.visibility = View.GONE
        shimmer_loading.visibility = View.GONE

    }

    private fun setupAdapter(){
        libroAdapter = LibroAdapter(viewModelLibro.libros.value?: emptyList(), this)
        rv_libros.layoutManager = LinearLayoutManager(this)
        rv_libros.adapter = libroAdapter
    }

    override fun onResume() {
        super.onResume()
        //viewModelLibro.consultarLibros()
        lifecycleScope.launch {
            viewModelLibro.consultarLibros()
        }
    }


}
