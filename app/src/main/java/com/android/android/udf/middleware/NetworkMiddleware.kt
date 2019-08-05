package com.android.android.udf.middleware

import com.android.android.udf.state.AppState
import org.rekotlin.Middleware

internal val NetworkMiddleware: Middleware<AppState> = { dispatch, state ->
    { next ->
        { action ->
            when (action) {
                // TODO: add the middleware we ned
            }
            next(action)
        }
    }
}