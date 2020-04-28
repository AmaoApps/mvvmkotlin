package pe.paku.mvvmkotlin.api

import kotlinx.coroutines.Deferred
import pe.paku.mvvmkotlin.beans.LibroBean
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Endpoint {

    @GET("get")
    fun obtenerBusqueda(@Query(value = "order") order:String) : Call<ArrayList<LibroBean>>

    @GET("get")
    fun obtenerUltimosLibros(@Query ("keyword") keyword : String) : Call<ArrayList<LibroBean>>

    @GET("get")
    fun obtenerBusquedaCoroutine(@Query(value = "order") order:String) : Deferred<ArrayList<LibroBean>>

    @GET("get")
    suspend fun obtenerBusquedav2(@Query(value = "order") order:String) : ArrayList<LibroBean>

}
