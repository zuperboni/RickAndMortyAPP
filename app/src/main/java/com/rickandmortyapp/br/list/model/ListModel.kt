package com.rickandmortyapp.br.list.model

import android.content.Context
import com.rickandmortyapp.br.configuration.RetrofitConfig
import com.rickandmortyapp.br.list.presentation.ListContract
import com.rickandmortyapp.br.model.CharacterList
import com.rickandmortyapp.br.remote.RickAndMortyApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ListModel : ListContract.Model {
    override fun fetchList(
        onFinishedListener: ListContract.Model.OnFinishedListener,
        context: Context
    ) {
        val api: RickAndMortyApi = RetrofitConfig.getClient(context = context).create(RickAndMortyApi::class.java)

        api.getCharactersList().enqueue(object : Callback<CharacterList> {
            override fun onResponse(
                call: Call<CharacterList>,
                response: Response<CharacterList>
            ) {
                response.body()?.let {
                    onFinishedListener.onFinished(it.characters)
                }
            }

            override fun onFailure(call: Call<CharacterList>, t: Throwable) {
                onFinishedListener.onFailure(t)
            }
        })
    }
}