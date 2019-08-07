package com.android.android.udf.store

import android.os.Handler
import android.util.Log
import com.android.android.udf.Actions.BaseAction
import com.android.android.udf.Actions.RemoveStateStatus
import com.android.android.udf.state.ActionStatus
import com.android.android.udf.state.AppState
import com.android.android.udf.state.getStateFlowStatusBySession
import org.rekotlin.StoreSubscriber
import java.util.*

interface  Base : StoreSubscriber<AppState> {
    val state: AppState
    var actionSessionIds: ArrayList<String>

    fun getActionId(): String {
        val actionId =  UUID.randomUUID().toString()
        actionSessionIds.add(actionId)
        return actionId
    }

    fun dispatchAction(action: BaseAction, showLoader: Boolean = true) {
        if(showLoader)
            showLoader()
        appStore.dispatch(action)
    }

    fun showLoader() {
        // TODO: Add show loader code when you implement the method
    }

    fun hideLoader() {
        val handler = Handler()
        handler.postDelayed({
            // TODO: Add hide loader code when you implement the method
        }, 50)
    }

    override fun newState(state: AppState) {
        val ids: ArrayList<String> = this.actionSessionIds.clone() as ArrayList<String>
        ids.forEach { updateActivity(state, it) }
    }

    private fun updateActivity(state: AppState, actionId: String) {
        getStateFlowStatusBySession(state, actionId)?.let { action ->
            if (action.status == ActionStatus.COMPLETED) {
                if (onStateUpdate(state, action))
                    hideLoader()
                appStore.dispatch(RemoveStateStatus.Perform(actionId, getActionId()))
            } else if (action.status == ActionStatus.ERROR) {
                onError(action)
                hideLoader()
                appStore.dispatch(RemoveStateStatus.Perform(actionId, getActionId()))
            }
        }
    }

    fun onStateUpdate(state: AppState, action: BaseAction): Boolean
    fun onError(action: BaseAction)
}