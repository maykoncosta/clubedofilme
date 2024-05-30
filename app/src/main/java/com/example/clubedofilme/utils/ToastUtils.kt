package com.example.clubedofilme.utils

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.clubedofilme.R

object ToastUtils {

    fun showCustomToast(context: Context, message: String, iconResId: Int) {
        val layoutInflater = LayoutInflater.from(context)
        val layout: View = layoutInflater.inflate(R.layout.custom_toast, null)

        val toastIcon: ImageView = layout.findViewById(R.id.toast_icon)
        val toastText: TextView = layout.findViewById(R.id.toast_text)

        toastIcon.setImageResource(iconResId)
        toastText.text = message

        with(Toast(context)) {
            duration = Toast.LENGTH_SHORT
            view = layout
            show()
        }
    }
}
