package com.android.android.udf.reducers

import com.android.android.udf.Actions.CalcDivide
import com.android.android.udf.state.AppState
import org.rekotlin.Action
//
//fun calcDivideReducer(action: Action, state: AppState): AppState {
//    when(action as CalcDivide) {
//        is CalcDivide.Perform -> {
//            val data = action as CalcDivide.Perform
//
//            val numToSubtract = data.numToDivide
//            val sourceInt = data.sourceInt
//            val localState = state.copy(calculatorValue = (sourceInt / numToSubtract))
//            return updateActionsStateStatus(
//                localState, action.getId(), CalcDivide.Success(action.getId())
//            )
//        }
//    }
//    return state
//}
