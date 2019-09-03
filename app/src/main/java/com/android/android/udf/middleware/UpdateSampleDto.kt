package com.android.android.udf.middleware

import android.util.Log
import com.android.android.udf.Actions.SampleAction
import org.rekotlin.DispatchFunction

fun updateSampleDto(dispatch: DispatchFunction, action: SampleAction.Request) {
    val service = apiService()

    val call = service.getProductLocations()

    call.enqueue(object : Callback<List<ApiPurchaseOrderProductLocation>> {
        override fun onResponse(call: Call<List<ApiPurchaseOrderProductLocation>>?, responseApi: Response<List<ApiPurchaseOrderProductLocation>>?) {
            responseApi?.let {
                if(responseApi.code() == 200) {
                    val productLocations: List<Location> = responseApi.body().map {
                        Log.i("productLocation", it.name + " " + it.id)
                        Location(it.id, it.name)

                    }
                    dispatch(GetLocations.Perform(productLocations, action.getId()))

                } else {
                    parseError(responseApi.errorBody())?.let {
                        throwError(it)
                    }
                }
            }
        }

        override fun onFailure(call: Call<List<ApiPurchaseOrderProductLocation>>?, t: Throwable?) {
            val apiError = ApiError(500, t?.message ?: "Unexpected Error")
            throwError(apiError)
        }

        fun throwError(error: ApiError) {
            // NB: on error, rather than throwing an error, we are going to generate the known locations for now.
            val productLocations: MutableList<Location> = ArrayList<Location>().toMutableList()
            // TODO: need to localize the names
            productLocations.add(Location(1, "Buttikk"))
            productLocations.add(Location(2, "Largar"))
            productLocations.add(Location(3, "Stot"))
            dispatch(GetLocations.Perform(productLocations, action.getId()))
        }
    })
}
