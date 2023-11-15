package com.ounicsoft.calmypenny.ui.utils

import android.content.Context
import android.widget.Toast


class MyToast(private val context: Context, private val message: String) {
    private var toast: Toast = Toast(context)
    fun showToast() {
        toast.cancel()
        toast = Toast.makeText(context, message, Toast.LENGTH_SHORT)
        toast.show()
    }
}