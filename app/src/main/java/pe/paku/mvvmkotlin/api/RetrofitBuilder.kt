package pe.paku.mvvmkotlin.api

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {

    private const val BASE_URL = "https://www.etnassoft.com/api/v1/"


    //Sin Coroutines
    private val retrofilBuilder : Retrofit.Builder by lazy {

        Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())

    }

    val apiService: Endpoint by lazy {
        retrofilBuilder.build().create(Endpoint::class.java)
    }

    //Con Coroutines
    private val retrofitBuilderCoroutine : Retrofit.Builder  by lazy {
        Retrofit.Builder().baseUrl(BASE_URL)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
    }

    val apiServiceCoroutine: Endpoint by lazy {
        retrofitBuilderCoroutine.build().create(Endpoint::class.java)
    }
}