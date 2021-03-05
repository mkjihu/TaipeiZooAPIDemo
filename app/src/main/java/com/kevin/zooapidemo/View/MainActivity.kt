package com.kevin.zooapidemo.View

import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.jakewharton.rxbinding3.view.clicks
import com.kevin.zooapidemo.Base.BaseActivity
import com.kevin.zooapidemo.Model.results
import com.kevin.zooapidemo.Presenter.MainPresenter
import com.kevin.zooapidemo.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.main_layout.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import org.jetbrains.anko.sdk27.coroutines.textChangedListener
import java.util.concurrent.TimeUnit


class MainActivity : BaseActivity() {


    lateinit var presenter: MainPresenter
    lateinit var mDrawerToggle: ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter = MainPresenter(this)
        fid()
    }

    private fun fid() {
        recycler_view.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity)
            addItemDecoration(DividerItemDecoration(this@MainActivity, DividerItemDecoration.VERTICAL))
        }
        mDrawerToggle = ActionBarDrawerToggle(this, drawer_layout, toolbar, R.string.drawer_open, R.string.drawer_close)
        drawer_layout.addDrawerListener(mDrawerToggle)
        mDrawerToggle.syncState()
        search_img.clicks().throttleFirst(500, TimeUnit.MILLISECONDS).subscribe{ search_ed.setText("") }
        search_ed.textChangedListener { onTextChanged { _, _, _, _ -> presenter.search_out(search_ed.text.toString().trim()) } }
    }
    override fun onDestroy() {
        super.onDestroy()
        presenter.destroy()
    }
}