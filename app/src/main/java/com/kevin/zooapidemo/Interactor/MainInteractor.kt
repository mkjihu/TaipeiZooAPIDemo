package com.kevin.zooapidemo.Interactor

import android.util.Log.i
import com.kevin.zooapidemo.Model.resourceAquire
import com.kevin.zooapidemo.Network.API_Url
import com.kevin.zooapidemo.Network.HttpApiClient
import com.kevin.zooapidemo.Presenter.MainPresenter
import com.tw.mjmaster.Base.*
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subscribers.DisposableSubscriber

class MainInteractor(p: MainPresenter): BaseInteractor<MainPresenter>(p){


    fun GetData(){
        var s = HttpApiClient.getInstance().GetData(API_Url.Api1)
            .subscribeOn(Schedulers.io())
            .doOnSubscribe { getPresenter().diashow() }
            .onBackpressureBuffer()
            .unsubscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSubscriber<resourceAquire>() {
                override fun onError(t: Throwable) { getPresenter().isErroe(t) }
                override fun onNext(it: resourceAquire?) {
                    getPresenter().mModel.setDataModel(it)
                }
                override fun onComplete() { getPresenter().diadism() }
            })

        getDisposable()?.add(s)
    }

}

