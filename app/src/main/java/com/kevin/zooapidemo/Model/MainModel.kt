package com.kevin.zooapidemo.Model

import android.app.Application
import androidx.annotation.NonNull
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

class MainModel(@NonNull application: Application) : AndroidViewModel(application)  {

    private var Data1 = MutableLiveData<resourceAquire>()

    fun setDataModel(a:resourceAquire?){ if (a !=null){
        //Data1.postValue(a)
        Data1.value = a
    } }
    fun getDataModel(): MutableLiveData<resourceAquire> { return Data1 }

}