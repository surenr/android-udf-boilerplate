package com.android.android.udf.CalculatorUnitTest

import com.android.android.udf.domain.ApiError
import com.android.android.udf.store.appStore
import org.awaitility.Awaitility.await
import org.junit.*
import org.junit.Assert.assertEquals
import org.junit.runners.MethodSorters
import java.util.concurrent.Callable
import java.util.concurrent.TimeUnit

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
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
        expected: Int
    ) {
        await().atMost(5, TimeUnit.SECONDS).until(resultUpdated(calInstance, expected))
        assertEquals(expected, calInstance.calcResult)
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

    private fun setDefaultResult(num: Int) {
        calTestActivity.calcResult = num
        assertEquals(num, calTestActivity.calcResult)
    }


    @Test
    fun calcAddTest() {
        setDefaultResult(0)
        calTestActivity.addNumber(5)
        assertAsyncSuccess(calTestActivity, 5)
    }

    @Test
    fun calAddNegativeNumThrowError() {
        setDefaultResult(5)
        calTestActivity.addNumber(-1)
        assertAsyncError(calTestActivity, ApiError(500, "Can not add negative number"), calTestActivity.calLastError!!)
    }

    @Test
    fun calcSubtract() {
        setDefaultResult(5)
        calTestActivity.subtractNumber(3)
        assertAsyncSuccess(calTestActivity, 2)
    }

    @Test
    fun calMultiplyTest() {
        setDefaultResult(2)
        calTestActivity.multiplyNumber(5)
        assertAsyncSuccess(calTestActivity, 10)
    }

    @Test
    fun calDivisionTest() {
        setDefaultResult(10)
        calTestActivity.divideNumber(2)
        assertAsyncSuccess(calTestActivity, 5)
    }
}