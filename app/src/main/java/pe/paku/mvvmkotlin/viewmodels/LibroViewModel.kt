package pe.paku.mvvmkotlin.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import pe.paku.mvvmkotlin.api.OperationCallback
import pe.paku.mvvmkotlin.api.Status
import pe.paku.mvvmkotlin.beans.LibroBean
import pe.paku.mvvmkotlin.data.LibroRepository

class LibroViewModel : ViewModel() {


    val libroRepository: LibroRepository = LibroRepository()


    //Va a escuchar por los cambios de:
    private val _libros = MutableLiveData<List<LibroBean>>().apply { value = emptyList() }
    // Cuando llega a una data nueva esta es la que va a cambiar -> Mutable
    val libros: LiveData<List<LibroBean>> get() = _libros

    //Cuando la variable _libros cambie, se le notificara a la funcion get
    // y la variable libros se modificara.

    private val _isViewLoading  = MutableLiveData<Boolean>()
    val isViewLoading : LiveData<Boolean> = _isViewLoading

    private val _onMessageError = MutableLiveData<Any>()
    val onMessageError : LiveData<Any> = _onMessageError

    private val _isEmptylist = MutableLiveData<Boolean>()
    val isEmptylist : LiveData<Boolean> = _isEmptylist

    suspend fun consultarLibros(){

        _isViewLoading.postValue(true)
        /*
        libroRepository.obtenerLibros(object : OperationCallback<ArrayList<LibroBean>>{

            override fun onSuccess(data: ArrayList<LibroBean>) {

                _isViewLoading.postValue(false)
                if(data!=null){
                    if(data.isEmpty()){
                        _isEmptylist.postValue(true)
                    }else {
                        _libros.value = data
                    }
                }
            }

            override fun onError(error: String?) {
                _isViewLoading.postValue(false)
                _onMessageError.postValue(error)
            }

        })
        */

        /* SIn manejo de erreoes
        _libros.value = libroRepository.obtenerLibrosSuspend()
        _isViewLoading.postValue(false)
         */

        /*Con Manejo de Errores */
        val respuesta = libroRepository.obtenerLibrosSuspend()
        val status = respuesta.status
        when(status){
            Status.SUCCESS -> _libros.value = respuesta.data
            Status.ERROR -> _onMessageError.postValue(respuesta.message)
            Status.LOADING ->  _isViewLoading.postValue(true)
        }

        _isViewLoading.postValue(false)

    }

}