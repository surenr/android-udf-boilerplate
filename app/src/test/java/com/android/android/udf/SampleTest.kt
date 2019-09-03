package com.android.android.udf

import com.android.android.udf.domain.ApiError
import com.android.android.udf.store.appStore
import org.awaitility.Awaitility.await
import org.junit.*
import org.junit.Assert.assertEquals
import org.junit.runners.MethodSorters
import java.util.concurrent.Callable
import java.util.concurrent.TimeUnit

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class SampleTest {

    companion object {
        init {
            // things that may need to be setup before companion class member variables are instantiated
        }

        // variables you initialize for the class just once:
        lateinit var sampleTestActivity: SampleTestActivity


        @BeforeClass @JvmStatic fun setup() {
            sampleTestActivity = SampleTestActivity()
            appStore.subscribe(sampleTestActivity)
        }

        @AfterClass @JvmStatic fun teardown() {
            appStore.unsubscribe(sampleTestActivity)
        }
    }

    private fun assertAsyncSuccess(
        calInstance: SampleTestActivity,
        expected: String
    ) {
        await().atMost(5, TimeUnit.SECONDS).until(resultUpdated(calInstance, expected))
        assertEquals(expected, calInstance.username)
    }

    private fun assertAsyncError(calInstance: SampleTestActivity, expected: ApiError, target: ApiError) {
        await().until(errorUpdated(calInstance))
        assertEquals(expected.code, target.code)
        assertEquals(expected.message, target.message)
    }

    private fun resultUpdated(calInstance: SampleTestActivity, target: String): Callable<Boolean> {
        return Callable {
            calInstance.username == target
        }
    }

    private fun errorUpdated(calInstance: SampleTestActivity): Callable<Boolean> {
        return Callable {
            calInstance.testError != null
        }
    }

    private fun setDefaultResult(username: String) {
        sampleTestActivity.username = username
        assertEquals(username, sampleTestActivity.username)
    }


    @Test
    fun testSampleDto() {
        setDefaultResult("")
        sampleTestActivity.updateName("success")
        assertAsyncSuccess(sampleTestActivity, "Sample Name")
    }

//    @Test
//    fun calAddNegativeNumThrowError() {
//        setDefaultResult(5)
//        calTestActivity.addNumber(-1)
//        assertAsyncError(calTestActivity, ApiError(500, "Can not add negative number"), calTestActivity.calLastError!!)
//    }


}