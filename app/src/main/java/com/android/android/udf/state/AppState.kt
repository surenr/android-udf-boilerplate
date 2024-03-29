package com.android.android.udf.state

import com.android.android.udf.Actions.BaseAction
import org.rekotlin.StateType


data class AppState(
    val calculatorValue: Int = 0,
    val systemStateUpdateTracker: Map<String, BaseAction> = hashMapOf()
): StateType

enum class ActionStatus {
    INIT, COMPLETED, ERROR
}

val getStateFlowStatusBySession: (state: AppState, sessionId: String) -> BaseAction? = {
        state, sessionId -> state.systemStateUpdateTracker[sessionId]
}

val getCalResult: (state: AppState) -> Int = { state -> state.calculatorValue }