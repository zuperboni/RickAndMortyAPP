package com.rickandmortyapp.br.detail.presentation

import android.content.Context
import com.rickandmortyapp.br.model.Character

interface DetailContract {

    interface Model {
        interface OnFinishedListener {
            fun onFinished(detail: Character)
            fun onFailure(error: Throwable)
        }

        fun fetchDetails(onFinishedListener: OnFinishedListener, context: Context, id: Int)
    }

    interface View {
        var presenter: DetailPresenter

        fun showError(error: Throwable)
    }

    interface Presenter {
        fun getDetails(id: Int)
    }
}