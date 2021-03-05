package com.kevin.zooapidemo.Adapter

import android.app.Activity
import android.util.Log.i
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.jakewharton.rxbinding3.view.clicks
import com.kevin.zooapidemo.Model.results
import com.kevin.zooapidemo.R
import com.kevin.zooapidemo.View.ParkPage
import kotlinx.android.synthetic.main.item_d1.view.*
import org.greenrobot.eventbus.EventBus
import org.jetbrains.anko.startActivity
import java.util.concurrent.TimeUnit


class ZooApi1Adapter(var a: Activity, var items : ArrayList<results>) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    var mLayoutInflater: LayoutInflater = LayoutInflater.from(a)

    override fun getItemCount(): Int {
        return  items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(mLayoutInflater.inflate(R.layout.item_d1, parent, false))
    }

    override fun onBindViewHolder(p0: RecyclerView.ViewHolder, position: Int) {
        (p0 as ViewHolder).setData(position)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun setData(position: Int) {
            items[position].apply {
                Glide.with(a)
                    .load("$E_Pic_URL")
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .placeholder(R.drawable.ic_btn_pic)
                    .error(R.drawable.ic_btn_pic)
                    .into(itemView.image)
                    .waitForLayout()

                itemView.tx1.text = "$E_Name"
                itemView.tx2.text = "$E_Info"
                itemView.tx3.text = if(E_Memo.isNullOrBlank()) "無休館資訊" else "$E_Memo"
                itemView.clicks().throttleFirst(1, TimeUnit.SECONDS).subscribe{ _ ->
                    EventBus.getDefault().postSticky(this)
                    a.startActivity<ParkPage>()
                    a.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                }
            }

        }
    }
    fun setArray(items: ArrayList<results>) {
        this.items = items
        notifyDataSetChanged()
    }
}