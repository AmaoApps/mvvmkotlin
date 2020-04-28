package pe.paku.mvvmkotlin.api

data class Recurso<out T>(
    val status: Status,
    val data: T?,
    val message: String?
) {
    companion object{
        fun <T> success(data: T?): Recurso<T> {
            return Recurso(Status.SUCCESS, data, null)
        }

        fun <T> error(msg: String, data: T?): Recurso<T> {
            return Recurso(Status.ERROR, data, msg)
        }

        fun <T> loading(data: T?): Recurso<T> {
            return Recurso(Status.LOADING, data, null)
        }
    }
}