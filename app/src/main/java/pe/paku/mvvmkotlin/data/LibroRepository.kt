package pe.paku.mvvmkotlin.data

import pe.paku.mvvmkotlin.api.OperationCallback
import pe.paku.mvvmkotlin.api.RetrofitBuilder
import pe.paku.mvvmkotlin.beans.LibroBean
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


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

    suspend fun obtenerLibrosSuspend(){
        RetrofitBuilder.apiService.obtenerBusquedav2("newest")
    }

    override fun obtenerUltimosLibros(callback: OperationCallback<List<LibroBean>>) {
        //RetrofitBuilder.apiService.obtenerUltimosLibros()
    }


}