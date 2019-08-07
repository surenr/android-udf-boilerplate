package com.android.android.udf.reducers

import com.android.android.udf.Actions.CalcSubtract
import com.android.android.udf.state.AppState
import org.rekotlin.Action

fun calcSubtractReducer(action: Action, state: AppState): AppState {
    when(action as CalcSubtract) {
        is CalcSubtract.Perform -> {
            val data = action as CalcSubtract.Perform

            val numToSubtract = data.numToSubtract
            val sourceInt = data.sourceInt
            val localState = state.copy(calculatorValue = (sourceInt - numToSubtract))
            return updateActionsStateStatus(
                localState, action.getId(), CalcSubtract.Success(action.getId())
            )
        }
    }
    return state
}
