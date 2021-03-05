package com.kevin.zooapidemo.Presenter


import androidx.lifecycle.ViewModelProvider
import com.kevin.zooapidemo.Adapter.ZooApi1Adapter
import com.kevin.zooapidemo.Interactor.MainInteractor
import com.kevin.zooapidemo.Model.MainModel
import com.kevin.zooapidemo.Model.resourceAquire
import com.kevin.zooapidemo.Model.results
import com.kevin.zooapidemo.View.MainActivity
import com.tw.mjmaster.Base.BasePresenter
import kotlinx.android.synthetic.main.main_layout.*

class MainPresenter(page : MainActivity) : BasePresenter<MainActivity>(page) {

    lateinit var interactor: MainInteractor
    lateinit var mModel: MainModel
    private var items: ArrayList<results>? = null
    lateinit var adapter:ZooApi1Adapter
    override fun init() {
        interactor = MainInteractor(this)
        mModel = ViewModelProvider(getView()).get(MainModel::class.java)
        observe()
        CallAPI()
    }

    private fun CallAPI() { interactor.GetData() }
    fun observe() { mModel.getDataModel().observe(getView(), { LoadUpdate(it) }) }

    private fun LoadUpdate(r: resourceAquire) {
        getView().apply {
            r.result?.results?.let {
                items = it
                adapter = ZooApi1Adapter(this,it)
                recycler_view.adapter = adapter
            }
        }
    }

    fun search_out(outit:String){
        if (outit.isBlank()){
            items?.run { adapter.setArray(this) }
        }else{
            items?.let { it1 ->
                var items_ = it1.filter { it2 -> it2.E_Name?.contains(outit) == true
                        || it2.E_Info?.contains(outit) == true }.toCollection(ArrayList())
                adapter.setArray(items_)
            }
        }
    }



}