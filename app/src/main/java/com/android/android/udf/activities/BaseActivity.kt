package com.android.android.udf.activities

import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import com.android.android.udf.dialogs.LoaderDialog
import com.android.android.udf.store.Base
import com.android.android.udf.store.appStore
import java.util.*

abstract class BaseActivity : AppCompatActivity(), Base {

    override val state = appStore.state
    lateinit var loaderDialog: LoaderDialog
    //TODO: Define any other dialogs you might need in the application such as Alert Dialogs, Permission Dialog etc.

    override var actionSessionIds: ArrayList<String> = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loaderDialog = LoaderDialog(this)
        //TODO: Instantiate other dialogs here.
    }

    override fun hideLoader() {
        val handler = Handler()
        handler.postDelayed({
            if (::loaderDialog.isInitialized) {
                loaderDialog.hideDialog()
            }
        }, 50)
    }

    override fun showLoader() {
        loaderDialog.showDialog()
    }

    override fun onStart() {
        super.onStart()
        appStore.subscribe(this)
    }

    override fun onStop() {
        super.onStop()
        appStore.unsubscribe(this)
    }
}