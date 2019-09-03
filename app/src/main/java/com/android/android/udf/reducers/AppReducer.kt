package com.android.android.udf.reducers

import com.android.android.udf.Actions.*
import com.android.android.udf.state.AppState
import org.rekotlin.Action

fun updateActionsStateStatus(state: AppState, actionId: String?, action: BaseAction): AppState {
    if(actionId != null) {
        val statusMap = state.systemStateUpdateTracker
            .toMutableMap()
            .filterKeys { it != actionId }
            .toMutableMap()
        statusMap[actionId] = action
        return state.copy(systemStateUpdateTracker = statusMap.toMap())
    }
    return state
}

fun appReducers(action: Action, state: AppState?): AppState {
    val appState = state ?: AppState()

    when (action) {
//        is CalcAdd -> {
//            return calcAddReducer(action, appState)
//        }
//        is CalcSubtract -> {
//            return calcSubtractReducer(action, appState)
//        }
//        is CalcMultiply -> {
//            return calcMultiplyReducer(action, appState)
//        }
//        is CalcDivide -> {
//            return calcDivideReducer(action, appState)
//        }
//        is RemoveStateStatus -> {
//            return removeStateStatus(action, appState)
//        }
    }
    return appState
}