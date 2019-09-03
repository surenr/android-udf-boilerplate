package com.android.android.udf.Actions

import com.android.android.udf.domain.ApiError
import com.android.android.udf.domain.SampleDTO
import com.android.android.udf.state.ActionStatus

sealed class CalcAdd(baseId: String? = "", actionStatus: ActionStatus? = ActionStatus.INIT, error: ApiError? = null): BaseAction(baseId, actionStatus, error) {
    class Perform(val sourceInt: Int, val numToAdd: Int, actionId: String?):  CalcAdd(baseId = actionId, actionStatus = ActionStatus.INIT)
    class Success(actionId: String?):  CalcAdd(baseId = actionId, actionStatus = ActionStatus.COMPLETED)
    class Failure(actionError: ApiError, actionId: String?): CalcAdd(baseId = actionId, actionStatus = ActionStatus.ERROR, error = actionError)
}

sealed class CalcSubtract(baseId: String? = "", actionStatus: ActionStatus? = ActionStatus.INIT, error: ApiError? = null): BaseAction(baseId, actionStatus, error) {
    class Perform(val sourceInt: Int, val numToSubtract: Int, actionId: String?):  CalcSubtract(baseId = actionId, actionStatus = ActionStatus.INIT)
    class Success(actionId: String?):  CalcSubtract(baseId = actionId, actionStatus = ActionStatus.COMPLETED)
}

sealed class CalcMultiply(baseId: String? = "", actionStatus: ActionStatus? = ActionStatus.INIT, error: ApiError? = null): BaseAction(baseId, actionStatus, error) {
    class Perform(val sourceInt: Int, val numToMultiply: Int, actionId: String?):  CalcMultiply(baseId = actionId, actionStatus = ActionStatus.INIT)
    class Success(actionId: String?):  CalcMultiply(baseId = actionId, actionStatus = ActionStatus.COMPLETED)
}

sealed class CalcDivide(baseId: String? = "", actionStatus: ActionStatus? = ActionStatus.INIT, error: ApiError? = null): BaseAction(baseId, actionStatus, error) {
    class Perform(val sourceInt: Int, val numToDivide: Int, actionId: String?):  CalcDivide(baseId = actionId, actionStatus = ActionStatus.INIT)
    class Success(actionId: String?):  CalcDivide(baseId = actionId, actionStatus = ActionStatus.COMPLETED)
}

sealed class SampleAction(baseId: String? = "", actionStatus: ActionStatus? = ActionStatus.INIT, error: ApiError? = null): BaseAction(baseId, actionStatus, error) {
    class Request(val gender: String, actionId: String?):  SampleAction(baseId = actionId, actionStatus = ActionStatus.INIT)
    class Perform(val sampleDto: SampleDTO, actionId: String?):  SampleAction(baseId = actionId, actionStatus = ActionStatus.INIT)
    class Success(actionId: String?):  SampleAction(baseId = actionId, actionStatus = ActionStatus.COMPLETED)
    class Failure(actionError: ApiError, actionId: String?): SampleAction(baseId = actionId, actionStatus = ActionStatus.ERROR, error = actionError)
}