package com.rickandmortyapp.br.configuration

import android.content.Context
import com.gustafah.android.mockinterceptor.MockConfig
import com.gustafah.android.mockinterceptor.MockInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

class RetrofitConfig {
    companion object {
        fun getClient(context: Context): Retrofit =
            Retrofit.Builder()
                .baseUrl("https://rickandmortyapi.com/api/")
                .client(OkHttpClient.Builder()
                    .addInterceptor(
                        HttpLoggingInterceptor().apply {
                            level = HttpLoggingInterceptor.Level.BODY
                        }
                    )
                    /*
                .addInterceptor(
                    MockInterceptor.apply {
                        config = MockConfig.Builder()
                            .suffix(".json")
                            .context { context }
                            .selectorMode(MockConfig.OptionsSelectorMode.ALWAYS_ON_TOP)
                            .build()
                    }
                )

                     */
                .build())
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
    }
}