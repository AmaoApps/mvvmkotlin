package pe.paku.mvvmkotlin.api

interface OperationCallback<T> {

    fun onSuccess(data:T)

    fun onError(error: String?)



}