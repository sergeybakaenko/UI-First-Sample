package com.formafit.architecture.network.retrofit

import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitService(private val baseUrl: String) : IRetrofitService {

    private val gson = GsonBuilder().setLenient().create()
    private fun getLoggingClient(): OkHttpClient =
        OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        }).build()

    private fun getRetrofit() = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(getLoggingClient())
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()

    override val api: Api = getRetrofit().create(Api::class.java)
}
