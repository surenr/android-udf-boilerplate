package com.android.android.udf.Actions

import com.android.android.udf.domain.ApiError
import com.android.android.udf.state.ActionStatus

sealed class CalcAdd(baseId: String? = "", actionStatus: ActionStatus? = ActionStatus.INIT, error: ApiError? = null): BaseAction(baseId, actionStatus, error) {
    class Perform(val sourceInt: Int, val numToAdd: Int, actionId: String?):  CalcAdd(baseId = actionId, actionStatus = ActionStatus.INIT)
    class Success(actionId: String?):  CalcAdd(baseId = actionId, actionStatus = ActionStatus.COMPLETED)
}