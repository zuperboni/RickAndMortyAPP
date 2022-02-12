package com.rickandmortyapp.br.detail.presentation

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.rickandmortyapp.br.detail.model.DetailModel
import com.rickandmortyapp.br.model.Character

class DetailPresenter(private val view: DetailContract.View, private val context: Context) : DetailContract.Presenter, DetailContract.Model.OnFinishedListener {

    private val detailsModel: DetailContract.Model = DetailModel()
    var character: Character? by mutableStateOf(null)
    var isLoading: Boolean by mutableStateOf(true)

    override fun getDetails(id: Int) {
        detailsModel.fetchDetails(this, context, id)
    }

    override fun onFinished(detail: Character) {
        character = detail
        isLoading = false
    }

    override fun onFailure(error: Throwable) {
        view.showError(error)
    }
}