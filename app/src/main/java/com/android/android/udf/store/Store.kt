package com.android.android.udf.store

import com.android.android.udf.middleware.NetworkMiddleware
import com.android.android.udf.reducers.appReducers
import org.rekotlin.Store

val appStore = Store(
    reducer = ::appReducers,
    state = null,
    middleware = listOf(NetworkMiddleware)
)