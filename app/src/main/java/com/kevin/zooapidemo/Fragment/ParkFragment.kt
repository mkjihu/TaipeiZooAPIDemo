package com.kevin.zooapidemo.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.kevin.zooapidemo.Model.results
import com.kevin.zooapidemo.R
import kotlinx.android.synthetic.main.fragment_park.*
import kotlinx.android.synthetic.main.item_d2.view.*

class ParkFragment(var results: results) : Fragment(), View.OnTouchListener {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view =  inflater.inflate(R.layout.fragment_park, null, false)//layoutId(inflater,container,savedInstanceState)
        view.setOnTouchListener(this)
        return view
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        fid()
    }

    private fun fid() {
        results.apply {
            Glide.with(activity!!).load("$F_Pic01_URL")
                .transition(DrawableTransitionOptions.withCrossFade())
                .placeholder(R.drawable.ic_btn_pic)
                .error(R.drawable.ic_btn_pic)
                .into(f_imageView)
                .waitForLayout()
            f_text1.text = "$F_Name_Ch"
            f_text2.text = "$F_Name_En"
            f_text3.text = "$F_AlsoKnown"
            f_text4.text = "$F_Brief"
            f_text5.text = "$F_Feature"
            f_text6.text = "$F_Functionn"
            f_text7.text = "${getString(R.string.text_script8)}$F_Update"
            
        }
      
    }


    override fun onTouch(v: View, event: MotionEvent) = true

}