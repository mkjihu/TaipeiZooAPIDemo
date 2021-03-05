package com.kevin.zooapidemo.Presenter

import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.kevin.zooapidemo.Adapter.ZooApi2Adapter
import com.kevin.zooapidemo.Fragment.ParkFragment
import com.kevin.zooapidemo.Interactor.ParkInteractor
import com.kevin.zooapidemo.Model.MainModel
import com.kevin.zooapidemo.Model.resourceAquire
import com.kevin.zooapidemo.Model.results
import com.kevin.zooapidemo.Object.ToWeb
import com.kevin.zooapidemo.R
import com.kevin.zooapidemo.View.ParkPage
import com.tw.mjmaster.Base.BasePresenter
import kotlinx.android.synthetic.main.activity_park_page.*
import kotlinx.android.synthetic.main.main_layout.recycler_view
import org.jetbrains.anko.sdk27.coroutines.onClick

class ParkPresenter(page : ParkPage) : BasePresenter<ParkPage>(page) {

    lateinit var interactor: ParkInteractor
    lateinit var mModel: MainModel
    var titles = ""
    override fun init() {
        interactor = ParkInteractor(this)
        mModel = ViewModelProvider(getView()).get(MainModel::class.java)
        observe()
    }
    fun observe() { mModel.getDataModel().observe(getView(), { LoadUpdate(it) }) }
    private fun CallAPI(itx:String) { interactor.GetData(itx) }

    fun hall_info(event: results) {
        getView().apply {
            Glide.with(this).load("${event.E_Pic_URL}")
                .transition(DrawableTransitionOptions.withCrossFade())
                .placeholder(R.drawable.ic_btn_pic).error(R.drawable.ic_btn_pic)
                .into(imageView).waitForLayout()
            title_view.text = "${event.E_Name}"
            text1.text = "${event.E_Info}"
            text2.text = if(event.E_Memo.isNullOrBlank()) "無休館資訊" else "${event.E_Memo}"
            text3.text = "${event.E_Category}"
            text4.onClick { ToWeb("${event.E_URL}") }
            presenter.CallAPI("${event.E_Name}")
        }
    }
    private fun LoadUpdate(r: resourceAquire) {
        getView().apply { r.result?.results?.let { recycler_view.adapter = ZooApi2Adapter(this,it){ v -> openinfo(v) } } }
    }


    private fun openinfo(it: results) {

        getView().apply {
            titles = "${title_view.text}"
            title_view.text = "${it.F_Name_Ch}"
            supportFragmentManager.beginTransaction().run {
                setCustomAnimations(R.anim.fragment_slide_left_enter, R.anim.fragment_slide_left_exit, R.anim.fragment_slide_right_enter, R.anim.fragment_slide_right_exit)
                replace(R.id.df_fay, ParkFragment(it),"ParkFragment")
                addToBackStack("ParkFragment")
                commit()
            }
        }
    }

    fun onback(){ getView().title_view.text = "$titles" }

}