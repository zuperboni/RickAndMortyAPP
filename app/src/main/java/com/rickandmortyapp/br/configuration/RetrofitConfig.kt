package com.rickandmortyapp.br.configuration

import android.content.Context
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

private const val URL = "https://rickandmortyapi.com/api/"

class RetrofitConfig {
    companion object {
        fun getClient(): Retrofit =
            Retrofit.Builder()
                .baseUrl(URL)
                .client(OkHttpClient.Builder()
                    .addInterceptor(
                        HttpLoggingInterceptor().apply {
                            level = HttpLoggingInterceptor.Level.BODY
                        }
                    )
                .build())
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
    }
}