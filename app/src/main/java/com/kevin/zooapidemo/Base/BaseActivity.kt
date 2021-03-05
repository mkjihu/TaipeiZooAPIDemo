package com.kevin.zooapidemo.Base

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kevin.zooapidemo.Object.DialogBox
import com.kevin.zooapidemo.Object.DialogBox.AlertDialog1
import com.kevin.zooapidemo.Object.DialogBox.AlertDialog2
import com.kevin.zooapidemo.R
import org.greenrobot.eventbus.EventBus
import java.net.UnknownHostException
import kotlin.Exception
import java.util.concurrent.TimeoutException


abstract class BaseActivity :AppCompatActivity() {

    lateinit var  dialog : Dialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dialog = DialogBox.loadDialog(this)
    }


    fun diashow(sir:Boolean = false) :Unit {
        try {
            dialog.setCancelable(sir)
            dialog.show()
        }catch (e:Exception){}
    }

    fun diadism()  {
        try {
            dialog.dismiss()
        }catch (e:Exception){}

    }
    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
    }



    fun isErroe(e: Throwable) {
        when {
            e is TimeoutException->{ AlertDialog1(this,"呼叫超時") }
            e is RuntimeException && e.message!!.contains("404")-> { AlertDialog1(this,"API無回應") }
            e is UnknownHostException -> { AlertDialog1(this, "無法連線，請檢查手機網路狀態或稍後再試","網路狀態異常") }
            e is RuntimeException -> { AlertDialog2(this, e.message!!) }
            else -> { AlertDialog2(this,"${e.message}") }
        }
    }
    override fun onDestroy() {
        EventBus.getDefault().unregister(this)
        super.onDestroy()
    }

}
