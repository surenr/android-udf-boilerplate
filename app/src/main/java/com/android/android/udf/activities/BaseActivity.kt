package com.android.android.udf.activities

import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import com.android.android.udf.Actions.BaseAction
import com.android.android.udf.Actions.RemoveStateStatus
import com.android.android.udf.dialogs.LoaderDialog
import com.android.android.udf.state.ActionStatus
import com.android.android.udf.state.AppState
import com.android.android.udf.state.getStateFlowStatusBySession
import com.android.android.udf.store.appStore
import org.rekotlin.StoreSubscriber
import java.util.*

abstract class BaseActivity : AppCompatActivity(), StoreSubscriber<AppState> {

    protected val state = appStore.state
    lateinit var loaderDialog: LoaderDialog
    //TODO: Define any other dialogs you might need in the application such as Alert Dialogs, Permission Dialog etc.

    private var actionSessionIds: ArrayList<String> = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loaderDialog = LoaderDialog(this)
        //TODO: Instantiate other dialogs here.
    }

    fun getActionId(): String {
        val actionId =  UUID.randomUUID().toString()
        actionSessionIds.add(actionId)
        return actionId
    }

    fun dispatchAction(action: BaseAction, showLoader: Boolean = true) {
        if(showLoader)
            loaderDialog.showDialog()
        appStore.dispatch(action)
    }

    private fun hideLoader() {
        val handler = Handler()
        handler.postDelayed({
            if (::loaderDialog.isInitialized) {
                loaderDialog.hideDialog()
            }
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


    override fun onStart() {
        super.onStart()
        appStore.subscribe(this)
    }

    override fun onStop() {
        super.onStop()
        appStore.unsubscribe(this)
    }

    abstract fun onStateUpdate(state: AppState, action: BaseAction): Boolean
    abstract fun onError(action: BaseAction)
}