package com.android.android.udf.CalculatorUnitTest

import com.android.android.udf.Actions.*
import com.android.android.udf.domain.ApiError
import com.android.android.udf.state.AppState
import com.android.android.udf.state.getCalResult
import com.android.android.udf.store.Base
import com.android.android.udf.store.appStore
import java.util.*

class CalculatorTestActivity : Base {
    override val state = appStore.state
    override var actionSessionIds: ArrayList<String> = ArrayList<String>()
    var calcResult = 0
    var calLastError: ApiError? = null
    override fun onStateUpdate(state: AppState, action: BaseAction): Boolean {
        when(action){
           // TODO: handle specific actions and update the activity as needed

        }
        calcResult = getCalResult(state)
        return true
    }

    override fun onError(action: BaseAction) {
        calLastError = action.getError()
    }

    fun addNumber(num: Int = 0) {
        dispatchAction(CalcAdd.Perform(calcResult, num, getActionId()))
    }

    fun subtractNumber(num: Int) {
        dispatchAction(CalcSubtract.Perform(calcResult, num, getActionId()))
    }

    fun multiplyNumber(num: Int) {
        dispatchAction(CalcMultiply.Perform(calcResult, num, getActionId()))
    }

    fun divideNumber(num: Int) {
        dispatchAction(CalcDivide.Perform(calcResult, num, getActionId()))
    }

}