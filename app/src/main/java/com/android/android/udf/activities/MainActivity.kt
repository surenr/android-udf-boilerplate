package com.android.android.udf.activities

import android.os.Bundle
import android.util.Log
import com.android.android.udf.Actions.*
import com.android.android.udf.R
import com.android.android.udf.state.AppState
import com.android.android.udf.state.getCalResult
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {
    override fun onStateUpdate(state: AppState, action: BaseAction): Boolean {
        when(action){
           // TODO: Handle any special actions here
        }
        calResult.text = getCalResult(state).toString()
        return true
    }

    override fun onError(action: BaseAction) {
        Log.e("error", "Activity reported error")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnAdd.setOnClickListener {
            val inputNumber = numberInput.text.toString().toInt()
            val existingNumber = if (calResult.text.toString().isEmpty()) 0 else calResult.text.toString().toInt()
            dispatchAction(CalcAdd.Perform(existingNumber, inputNumber, getActionId()))
        }

        btnSubtract.setOnClickListener {
            val inputNumber = numberInput.text.toString().toInt()
            val existingNumber = if (calResult.text.toString().isEmpty()) 0 else calResult.text.toString().toInt()
            dispatchAction(CalcSubtract.Perform(existingNumber, inputNumber, getActionId()))
        }

        btnMultiply.setOnClickListener {
            val inputNumber = numberInput.text.toString().toInt()
            val existingNumber = if (calResult.text.toString().isEmpty()) 0 else calResult.text.toString().toInt()
            dispatchAction(CalcMultiply.Perform(existingNumber, inputNumber, getActionId()))
        }

        btnDivision.setOnClickListener {
            val inputNumber = numberInput.text.toString().toInt()
            val existingNumber = if (calResult.text.toString().isEmpty()) 0 else calResult.text.toString().toInt()
            dispatchAction(CalcDivide.Perform(existingNumber, inputNumber, getActionId()))
        }
    }
}
