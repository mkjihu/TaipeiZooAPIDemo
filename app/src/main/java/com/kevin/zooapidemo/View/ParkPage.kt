package com.kevin.zooapidemo.View

import android.graphics.Paint
import android.os.Bundle
import android.util.Log.i
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.kevin.zooapidemo.Base.BaseActivity
import com.kevin.zooapidemo.Model.results
import com.kevin.zooapidemo.Presenter.ParkPresenter
import com.kevin.zooapidemo.R
import kotlinx.android.synthetic.main.activity_park_page.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import org.jetbrains.anko.sdk27.coroutines.onClick

class ParkPage : BaseActivity() {

    lateinit var presenter: ParkPresenter
    lateinit var evenit: results
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_park_page)
        EventBus.getDefault().register(this)
        presenter = ParkPresenter(this)
        fid()
    }

    private fun fid() {
        df_back_item.onClick { onBackPressed() }
        text4.paintFlags = Paint.UNDERLINE_TEXT_FLAG
        recycler_view.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@ParkPage)
            addItemDecoration(DividerItemDecoration(this@ParkPage, DividerItemDecoration.VERTICAL))
        }
        presenter.hall_info(evenit)
    }


    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    fun onDateEvent(event: results) {
        evenit = event
        EventBus.getDefault().removeStickyEvent(event)
    }

    override fun onBackPressed() {
        presenter.onback()
        super.onBackPressed()
    }
    override fun onDestroy() {
        super.onDestroy()
        presenter.destroy()
        EventBus.getDefault().unregister(this);
    }
}