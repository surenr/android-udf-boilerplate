package com.android.android.udf.activities

import android.os.Bundle
import android.util.Log
import com.android.android.udf.Actions.BaseAction
import com.android.android.udf.R
import com.android.android.udf.state.AppState

class MainActivity : BaseActivity() {
    override fun onStateUpdate(state: AppState, action: BaseAction): Boolean {
        return true
    }

    override fun onError(action: BaseAction) {
        Log.e("error", "Activity reported error")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
