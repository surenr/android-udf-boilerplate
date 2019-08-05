package com.android.android.udf.dialogs

import android.app.Activity
import android.app.Dialog
import android.view.Window
import com.android.android.udf.R

class LoaderDialog(private val context: Activity) {
    private lateinit var dialog: Dialog

    fun showDialog() {
        dialog = Dialog(context)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.dialog_loading)
        dialog.show()
    }

    fun hideDialog() {
        if(::dialog.isInitialized) {
            dialog.dismiss()
        }
    }
}