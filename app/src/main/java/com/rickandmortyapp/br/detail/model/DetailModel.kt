package com.rickandmortyapp.br.detail.model

import android.content.Context
import com.rickandmortyapp.br.configuration.RetrofitConfig
import com.rickandmortyapp.br.remote.RickAndMortyApi
import com.rickandmortyapp.br.detail.presentation.DetailContract
import com.rickandmortyapp.br.model.Character
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailModel : DetailContract.Model {
    override fun fetchDetails(onFinishedListener: DetailContract.Model.OnFinishedListener, context: Context, id: Int) {
        val api: RickAndMortyApi = RetrofitConfig.getClient(context).create(RickAndMortyApi::class.java)

        api.getCharacter(id).enqueue(object : Callback<Character> {
            override fun onResponse(call: Call<Character>, response: Response<Character>) {
                response.body()?.let {
                    onFinishedListener.onFinished(it)
                }
            }

            override fun onFailure(call: Call<Character>, t: Throwable) {
                onFinishedListener.onFailure(t)
            }

        })
    }
}