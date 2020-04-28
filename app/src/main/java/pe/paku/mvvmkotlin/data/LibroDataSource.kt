package pe.paku.mvvmkotlin.data

import pe.paku.mvvmkotlin.api.OperationCallback
import pe.paku.mvvmkotlin.beans.LibroBean

interface LibroDataSource {

    fun obtenerLibros(callback : OperationCallback<ArrayList<LibroBean>>)

    fun obtenerUltimosLibros(callback: OperationCallback<List<LibroBean>>)

}