package com.android.android.udf

import com.android.android.udf.Actions.*
import com.android.android.udf.domain.ApiError
import com.android.android.udf.state.AppState
import com.android.android.udf.store.Base
import com.android.android.udf.store.appStore
import java.util.*

class SampleTestActivity : Base {
    override val state = appStore.state
    override var actionSessionIds: ArrayList<String> = ArrayList<String>()

    var username = ""
    var testError: ApiError? = null
    override fun onStateUpdate(state: AppState, action: BaseAction): Boolean {
        when(action){
           // TODO: handle specific actions and update the activity as needed
            is SampleAction -> {
                this.username = state.sampleDto.name
                return true
            }

        }
        return false
    }

    override fun onError(action: BaseAction) {
        testError = action.getError()
    }


    fun updateName(gender: String) {
        dispatchAction(SampleAction.Request(gender, getActionId()))
    }

}