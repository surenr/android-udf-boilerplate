package com.android.android.udf.reducers

import com.android.android.udf.Actions.RemoveStateStatus
import com.android.android.udf.state.AppState

import org.rekotlin.Action

fun removeStateStatus(action: Action, state: AppState): AppState {
    when(action as RemoveStateStatus) {
        is RemoveStateStatus.Perform -> {
            val data = action as RemoveStateStatus.Perform
            val statusMap = state.systemStateUpdateTracker
                .toMutableMap()
                .filterKeys { it != data.uuid }
            return state.copy(systemStateUpdateTracker = statusMap) //updateActionsStateStatus(localState, action.getId(), RemoveStateStatus.Success(action.getId()!!))
        }

    }
    return state
}