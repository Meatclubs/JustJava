package com.wordpress.meatclubs.justjava

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.text.NumberFormat
import android.view.Gravity
import android.util.TypedValue





class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    fun increaseQuantity(view: android.view.View) {
        val quantity = quantityValueTextView.text.toString().toInt() + 1
        quantityValueTextView.setText(quantity.toString())
        updateCost(view)
    }

    fun decreaseQuantity(view: android.view.View) {
        var quantity = quantityValueTextView.text.toString().toInt() - 1
        if(quantity <0)
            quantity = 0

        quantityValueTextView.setText(quantity.toString())
        updateCost(view)
    }

    fun creamOptionChange(view: android.view.View) {
        if(creamCheckBox.isChecked) {
            creamCostTextView.visibility = android.view.View.VISIBLE
        } else {
            creamCostTextView.visibility = android.view.View.INVISIBLE
        }

        val tempTypedValue = TypedValue()
        resources.getValue(R.dimen.creamCost, tempTypedValue, true)
        val creamCostText = "+" + NumberFormat.getCurrencyInstance().format(tempTypedValue.float)
        creamCostTextView.text = creamCostText

        updateCost(view)
    }

    fun sugarOptionChange(view: android.view.View) {
        if(sugarCheckBox.isChecked) {
            sugarCostTextView.visibility = android.view.View.VISIBLE
        } else {
            sugarCostTextView.visibility = android.view.View.INVISIBLE
        }

        val tempTypedValue = TypedValue()
        resources.getValue(R.dimen.sugarCost, tempTypedValue, true)
        val sugarCostText = "+" + NumberFormat.getCurrencyInstance().format(tempTypedValue.float)
        sugarCostTextView.text = sugarCostText

        updateCost(view)
    }

    fun milkOptionChange(view: android.view.View) {
        if(milkCheckBox.isChecked) {
            milkCostTextView.visibility = android.view.View.VISIBLE
        } else {
            milkCostTextView.visibility = android.view.View.INVISIBLE
        }

        val tempTypedValue = TypedValue()
        resources.getValue(R.dimen.milkCost, tempTypedValue, true)
        val milkCostText = "+" + NumberFormat.getCurrencyInstance().format(tempTypedValue.float)
        milkCostTextView.text = milkCostText

        updateCost(view)
    }

    private fun updateCost(view: android.view.View) {
        val tempTypedValue = TypedValue()
        resources.getValue(R.dimen.coffeeCost, tempTypedValue, true)
        var totalCost = tempTypedValue.float

        if(milkCheckBox.isChecked) {
            resources.getValue(R.dimen.milkCost, tempTypedValue, true)
            totalCost += tempTypedValue.float
        }
        if(sugarCheckBox.isChecked) {
            resources.getValue(R.dimen.sugarCost, tempTypedValue, true)
            totalCost += tempTypedValue.float
        }
        if(creamCheckBox.isChecked){
            resources.getValue(R.dimen.creamCost, tempTypedValue, true)
            totalCost += tempTypedValue.float
        }

        totalCost *= quantityValueTextView.text.toString().toInt()
        val totalCostText = NumberFormat.getCurrencyInstance().format(totalCost)
        totalCostTextView.text = totalCostText
    }

    fun submitOrder(view: android.view.View) {
        val coffeeOrderedMessage ="Coffee Order Placed!"
        val noCoffeeMessage = "No Coffee Ordered"

        val toast: Toast
        if(quantityValueTextView.text.toString().toInt() > 0)
            toast = Toast.makeText(getApplicationContext(), coffeeOrderedMessage, Toast.LENGTH_SHORT)
        else
            toast = Toast.makeText(getApplicationContext(), noCoffeeMessage, Toast.LENGTH_SHORT)

        toast.setGravity(Gravity.BOTTOM, 0, 200)
        toast.show()
    }
}
