package com.tw.mjmaster.Base

import com.kevin.zooapidemo.Base.BaseActivity
import io.reactivex.rxjava3.disposables.CompositeDisposable


abstract class BasePresenter<V: BaseActivity>(private var view: V)  {

    var disposable: CompositeDisposable? = null
        private set

    init {
        setView(view!!)
        init()
    }
    fun getView(): V {
        return view
    }
    fun setView(view: V) {
        disposable = CompositeDisposable()
        this.view = view
    }

    fun destroy() { try { disposable?.dispose() } catch (e: Exception) {} }

    abstract fun init()
    fun diashow() = view.diashow()
    fun diadism() = view.diadism()
    fun isErroe(e: Throwable) = view.isErroe(e)

}
