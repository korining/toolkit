package com.korining.utils

import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.TextView
import android.widget.Toast
import java.lang.ref.WeakReference

/**
 * Created by Korining on 2019/1/30.
 *
 */
object ToastUtil {

    private var mToast: Toast? = null

    fun showWarningToast(context: Context, message: String?) {
        try {
            if (mToast != null) {
                cancel()
            }
            val weakReference = WeakReference(context.applicationContext)
            val toastView = LayoutInflater.from(weakReference.get()).inflate(R.layout.toast_layout, null)
            val showMessage = toastView.findViewById<TextView>(R.id.tv_content)
            showMessage.text = message
            mToast = Toast.makeText(weakReference.get(), message, Toast.LENGTH_SHORT)
            mToast?.setGravity(Gravity.CENTER, 0, 0)
            mToast?.view = toastView
            mToast?.show()
        } catch (e: NullPointerException) {
            e.printStackTrace()
        }
    }

    fun cancel() {
        if (mToast != null) {
            mToast?.cancel()
            mToast = null
        }
    }
}