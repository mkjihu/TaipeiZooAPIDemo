package com.kevin.zooapidemo.Object

import android.app.Activity
import android.content.Context
import android.app.Dialog
import android.content.Intent
import android.net.Uri
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.kevin.zooapidemo.R
import org.jetbrains.anko.*


object DialogBox {

    fun loadDialog(context: Context, title: String? = null): Dialog {
        val view = DialogUI<Context>().bind(context)
        val dial =  view.find<TextView>(R.id.dialog_tv1)
        if (title !=null) dial.text = title
        val dialog = Dialog(context, R.style.MyDialog)
        dialog.setContentView(view)
        dialog.setCanceledOnTouchOutside(false)
        dialog.setCancelable(false)
        return dialog
    }
    fun AlertDialog1(context: Context, message: String, title: String? = null, buttonText:String = "確定") {
        context.alert(message,title){
            this.isCancelable =false
            positiveButton(buttonText) {}
        }.show()
    }
    fun AlertDialog2(activity: Activity, message: String) {
        activity.alert(message){
            this.isCancelable =false
            positiveButton("確定") {
                activity.finish()
            }
        }.show()
    }

}

inline fun Activity.ToWeb(url:String){
    var intent = Intent(Intent.ACTION_VIEW, Uri.parse("$url"))
    startActivity(intent)
}


class DialogUI<V:Context> {

    fun bind(ui: V): View =
        ui.UI {
            linearLayout {
                orientation = LinearLayout.VERTICAL

                frameLayout {

                    linearLayout {
                        orientation = LinearLayout.VERTICAL

                        progressBar {
                            id = R.id.progress_bar
                            attr(android.R.attr.progressBarStyleLarge)
                        }.lparams(width = dip(65), height = dip(65)) {
                            gravity = Gravity.CENTER
                        }

                        textView("") {
                            id = R.id.dialog_tv1
                            textColor = R.color.ed_textcolor
                            textSize = 16.5f
                        }.lparams(width = wrapContent, height = wrapContent) {
                            gravity = Gravity.CENTER
                            topMargin = dip(5)
                        }


                    }.lparams(width = wrapContent, height = wrapContent){
                        bottomMargin = dip(5)
                        topMargin = dip(5)
                        leftMargin = dip(25)
                        rightMargin =  dip(25)
                        gravity = Gravity.CENTER

                    }
                }.lparams(width = wrapContent, height = wrapContent) {
                    gravity = Gravity.CENTER
                }
            }
        }.view
}



