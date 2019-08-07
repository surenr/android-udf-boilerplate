package com.android.android.udf.reducers

import com.android.android.udf.Actions.CalcAdd
import com.android.android.udf.state.AppState
import org.rekotlin.Action

fun calcAddReducer(action: Action, state: AppState): AppState {
    when(action as CalcAdd) {
        is CalcAdd.Perform -> {
            val data = action as CalcAdd.Perform
            val newResult = data.sourceInt + data.numToAdd
            val localState = state.copy(calculatorValue = newResult)
            return updateActionsStateStatus(
                localState, action.getId(), CalcAdd.Success(action.getId())
            )
        }
    }
    return state
}