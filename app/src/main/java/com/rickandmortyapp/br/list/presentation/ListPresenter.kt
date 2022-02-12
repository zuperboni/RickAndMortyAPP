package com.rickandmortyapp.br.list.presentation

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.rickandmortyapp.br.list.model.ListModel
import com.rickandmortyapp.br.model.Character

class ListPresenter(private val view: ListContract.View, private val context: Context) : ListContract.Presenter, ListContract.Model.OnFinishedListener {

    var characters: List<Character> by mutableStateOf(listOf())
    var isLoading: Boolean by mutableStateOf(true)

    private val listModel: ListContract.Model = ListModel()

    override fun onFinished(list: List<Character>) {
        characters = list
        isLoading = false
    }

    override fun onFailure(error: Throwable) {
        view.showError(error)
    }

    override fun getList() {
        listModel.fetchList(this, context)
    }

}