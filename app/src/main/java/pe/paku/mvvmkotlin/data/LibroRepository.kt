package pe.paku.mvvmkotlin.data

import pe.paku.mvvmkotlin.api.OperationCallback
import pe.paku.mvvmkotlin.api.Recurso
import pe.paku.mvvmkotlin.api.ResponseHandler
import pe.paku.mvvmkotlin.api.RetrofitBuilder
import pe.paku.mvvmkotlin.beans.LibroBean
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception


class LibroRepository : LibroDataSource {


    override fun obtenerLibros(callback: OperationCallback<ArrayList<LibroBean>>) {

        RetrofitBuilder.apiService.obtenerBusqueda("newest")
            .enqueue(object : Callback<ArrayList<LibroBean>> {
                override fun onResponse(
                    call: Call<ArrayList<LibroBean>>,
                    response: Response<ArrayList<LibroBean>>
                ) {
                    response.body()?.let {
                        if(response.isSuccessful){
                            callback.onSuccess(it)
                        } else {
                            callback.onError("No se pudo obtener los libros")
                        }
                    }
                }

                override fun onFailure(call: Call<ArrayList<LibroBean>>, t: Throwable) {

                    callback.onError(t.message)

                }

            })

    }

    suspend fun obtenerLibrosSuspend(): Recurso<ArrayList<LibroBean>> {
        //Sin manejo de errores -  todo bien
        //RetrofitBuilder.apiServiceCoroutine.obtenerBusquedav2("newest")

        //Con Manejo de errores
        return try {
            return ResponseHandler().handleSuccess(RetrofitBuilder.apiServiceCoroutine.obtenerBusquedav2("newest"))
        } catch (e: Exception) {
            return ResponseHandler().handleException(e)
        }

    }

    override fun obtenerUltimosLibros(callback: OperationCallback<List<LibroBean>>) {
        //RetrofitBuilder.apiService.obtenerUltimosLibros()
    }


}