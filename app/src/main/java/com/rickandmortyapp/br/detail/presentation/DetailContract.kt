package com.rickandmortyapp.br.detail.presentation

import android.content.Context
import com.rickandmortyapp.br.model.Character
import com.rickandmortyapp.br.mvp.BaseModel
import com.rickandmortyapp.br.mvp.BasePresenter
import com.rickandmortyapp.br.mvp.BaseView

interface DetailContract {

    interface Model: BaseModel {
        interface OnFinishedListener {
            fun onFinished(detail: Character)
            fun onFailure(error: Throwable)
        }

        fun fetchDetails(onFinishedListener: OnFinishedListener, context: Context, id: Int)
    }

    interface View: BaseView<DetailPresenter> {
        fun showError(error: Throwable)
    }

    interface Presenter: BasePresenter {
        fun getDetails(id: Int)
    }
}