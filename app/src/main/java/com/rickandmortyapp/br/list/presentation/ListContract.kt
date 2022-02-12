package com.rickandmortyapp.br.list.presentation

import android.content.Context
import com.rickandmortyapp.br.model.Character
import com.rickandmortyapp.br.mvp.BaseModel
import com.rickandmortyapp.br.mvp.BasePresenter
import com.rickandmortyapp.br.mvp.BaseView

interface ListContract {
    interface Model: BaseModel {
        interface OnFinishedListener {
            fun onFinished(list: List<Character>)
            fun onFailure(error: Throwable)
        }

        fun fetchList(onFinishedListener: OnFinishedListener, context: Context)
    }

    interface View: BaseView<ListPresenter> {
        fun showError(error: Throwable)
        fun startDetailActivity(id: Int)
    }

    interface Presenter: BasePresenter {
        fun getList()
    }
}