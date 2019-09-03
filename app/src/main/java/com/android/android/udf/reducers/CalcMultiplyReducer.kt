package com.android.android.udf.reducers

import com.android.android.udf.Actions.CalcMultiply
import com.android.android.udf.Actions.CalcSubtract
import com.android.android.udf.state.AppState
import org.rekotlin.Action

//fun calcMultiplyReducer(action: Action, state: AppState): AppState {
//    when(action as CalcMultiply) {
//        is CalcMultiply.Perform -> {
//            val data = action as CalcMultiply.Perform
//
//            val numToSubtract = data.numToMultiply
//            val sourceInt = data.sourceInt
//            val localState = state.copy(calculatorValue = (sourceInt * numToSubtract))
//            return updateActionsStateStatus(
//                localState, action.getId(), CalcMultiply.Success(action.getId())
//            )
//        }
//    }
//    return state
//}
