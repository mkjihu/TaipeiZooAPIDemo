package com.kevin.zooapidemo.Adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.jakewharton.rxbinding3.view.clicks
import com.kevin.zooapidemo.Model.results
import com.kevin.zooapidemo.R
import kotlinx.android.synthetic.main.item_d2.view.*
import java.util.concurrent.TimeUnit


class ZooApi2Adapter(var a: Activity, var items: ArrayList<results>,var function: (results) -> Unit) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    var mLayoutInflater: LayoutInflater = LayoutInflater.from(a)

    override fun getItemCount() = items.size
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(mLayoutInflater.inflate(R.layout.item_d2, parent, false))
    }
    override fun onBindViewHolder(p0: RecyclerView.ViewHolder, position: Int) { (p0 as ViewHolder).setData(position) }
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun setData(position: Int) {
            items[position].apply {
                Glide.with(a).load("$F_Pic01_URL")
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .placeholder(R.drawable.ic_btn_pic)
                    .error(R.drawable.ic_btn_pic)
                    .into(itemView.image)
                    .waitForLayout()
                itemView.tx1.text = "$F_Name_Ch"
                itemView.tx2.text = "$F_Feature"
                itemView.clicks().throttleFirst(800, TimeUnit.SECONDS).subscribe{ function(this) }
            }

        }
    }
}