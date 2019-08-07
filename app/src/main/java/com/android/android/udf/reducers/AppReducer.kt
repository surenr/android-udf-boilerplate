package com.android.android.udf.reducers

import com.android.android.udf.Actions.BaseAction
import com.android.android.udf.Actions.CalcAdd
import com.android.android.udf.state.AppState
import org.rekotlin.Action

fun updateActionsStateStatus(state: AppState, actionId: String?, action: BaseAction): AppState {
    if(actionId != null) {
        val statusMap = state.systemStateUpdateTracker
            .toMutableMap()
            .filterKeys { it != actionId!! }
            .toMutableMap()
        statusMap[actionId!!] = action
        return state.copy(systemStateUpdateTracker = statusMap.toMap())
    }
    return state
}

fun appReducers(action: Action, state: AppState?): AppState {
    var state = state ?: AppState()

    when (action) {
        is CalcAdd -> {
            val newState =  calcAddReducer(action, state)
            return newState
        }
    }
    return state
}