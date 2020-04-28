package pe.paku.mvvmkotlin.api

import retrofit2.HttpException
import java.net.SocketTimeoutException

enum class ErrorCodes(val code: Int) {
    SocketTimeOut(-1)
}

open class ResponseHandler {

    fun <T:Any> handleSuccess(data: T): Recurso<T>{
        return Recurso.success(data)
    }

    fun <T : Any> handleException(e: Exception): Recurso<T> {
        return when (e) {
            is HttpException -> Recurso.error(getErrorMessage(e.code()), null)
            is SocketTimeoutException -> Recurso.error(getErrorMessage(ErrorCodes.SocketTimeOut.code), null)
            else -> Recurso.error(getErrorMessage(Int.MAX_VALUE), null)
        }
    }

    private fun getErrorMessage(code: Int): String {
        return when (code) {
            ErrorCodes.SocketTimeOut.code -> "Timeout"
            401 -> "Unauthorised"
            404 -> "Not found"
            else -> "Something went wrong"
        }
    }

}