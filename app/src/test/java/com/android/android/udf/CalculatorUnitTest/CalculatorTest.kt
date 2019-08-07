package com.android.android.udf.CalculatorUnitTest

import com.android.android.udf.domain.ApiError
import com.android.android.udf.store.appStore
import org.awaitility.Awaitility.await
import org.junit.*
import org.junit.Assert.assertEquals
import java.util.concurrent.Callable

class CalculatorTest {

    companion object {
        init {
            // things that may need to be setup before companion class member variables are instantiated
        }

        // variables you initialize for the class just once:
        lateinit var calTestActivity: CalculatorTestActivity


        @BeforeClass @JvmStatic fun setup() {
            calTestActivity = CalculatorTestActivity()
            appStore.subscribe(calTestActivity)
        }

        @AfterClass @JvmStatic fun teardown() {
            appStore.unsubscribe(calTestActivity)
        }
    }

    private fun assertAsyncSuccess(
        calInstance: CalculatorTestActivity,
        expected: Int,
        target: Int
    ) {
        await().until(resultUpdated(calInstance, expected))
        assertEquals(expected, target)
    }

    private fun assertAsyncError(calInstance: CalculatorTestActivity, expected: ApiError, target: ApiError) {
        await().until(errorUpdated(calInstance))
        assertEquals(expected.code, target.code)
        assertEquals(expected.message, target.message)
    }

    private fun resultUpdated(calInstance: CalculatorTestActivity, target: Int): Callable<Boolean> {
        return Callable {
            calInstance.calcResult == target
        }
    }

    private fun errorUpdated(calInstance: CalculatorTestActivity): Callable<Boolean> {
        return Callable {
            calInstance.calLastError != null
        }
    }


    @Test
    fun calcAddTest() {
        assertEquals(0, calTestActivity.calcResult)
        calTestActivity.addNumber(5)
        assertAsyncSuccess(calTestActivity, 5, calTestActivity.calcResult)
    }

    @Test
    fun calAddNegativeNumThrowError() {
        assertEquals(5, calTestActivity.calcResult)
        calTestActivity.addNumber(-1)
        assertAsyncError(calTestActivity, ApiError(500, "Can not add negative number"), calTestActivity.calLastError!!)
    }

    @Test
    fun calcSubtract() {
        assertEquals(5, calTestActivity.calcResult)
        calTestActivity.subtractNumber(3)
        assertAsyncSuccess(calTestActivity, 2, calTestActivity.calcResult)
    }

}