package com.rickandmortyapp.br.list.presentation

import android.content.Context
import com.rickandmortyapp.br.model.Character

interface ListContract {
    interface Model {
        interface OnFinishedListener {
            fun onFinished(list: List<Character>)
            fun onFailure(error: Throwable)
        }

        fun fetchList(onFinishedListener: OnFinishedListener, context: Context)
    }

    interface View {
        var presenter: ListPresenter
        fun showError(error: Throwable)
        fun startDetailActivity(id: Int)
    }

    interface Presenter {
        fun getList()
    }
}