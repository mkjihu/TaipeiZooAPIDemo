package com.tw.mjmaster.Base

import android.content.Context
import com.kevin.zooapidemo.Base.BaseActivity
import io.reactivex.rxjava3.disposables.CompositeDisposable


abstract class BaseInteractor<P: BasePresenter<out BaseActivity>>(private var presenter: P)  {


    fun getDisposable(): CompositeDisposable? = presenter.disposable
    fun getView(): Context = presenter.getView()

    init {
        setPresenter(presenter!!)
    }

    fun getPresenter(): P {
        return presenter
    }

    fun setPresenter(p: P) {
        this.presenter = p
    }

}
