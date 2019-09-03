package com.android.android.udf.reducers

import com.android.android.udf.Actions.CalcAdd
import com.android.android.udf.domain.ApiError
import com.android.android.udf.state.AppState
import org.rekotlin.Action

//fun calcAddReducer(action: Action, state: AppState): AppState {
//    when(action as CalcAdd) {
//        is CalcAdd.Perform -> {
//            val data = action as CalcAdd.Perform
//
//            val numToAdd = data.numToAdd
//            val sourceInt = data.sourceInt
//            if(numToAdd < 0) return getNegativeNumberErrorState(state, action)
//            val localState = state.copy(calculatorValue = (sourceInt + numToAdd))
//            return updateActionsStateStatus(
//                localState, action.getId(), CalcAdd.Success(action.getId())
//            )
//        }
//    }
//    return state
//}

private fun getNegativeNumberErrorState(
    state: AppState,
    action: CalcAdd
): AppState {
    return updateActionsStateStatus(
        state,
        action.getId(),
        CalcAdd.Failure(ApiError(500, "Can not add negative number"), action.getId())
    )
}