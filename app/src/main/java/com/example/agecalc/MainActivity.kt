package com.example.agecalc

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    @SuppressLint("SimpleDateFormat")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        age_calc.setOnClickListener {

            val handler = Handler()
            handler.postDelayed(object : Runnable{
                override fun run() {
                    ageClalc()
                    handler.postDelayed(this,100)
                }
            },0)
        }
    }

    fun ageClalc(){

        val today = Date()
        val dobs = editTextTextPersonName.text.toString()
        val sdf = SimpleDateFormat("dd/MM/yyy HH:mm")
        val dob = sdf.parse(dobs)

        val week = (today.time - dob.time)/604800000
        val days = (today.time - dob.time)/86400000
        val hours = (today.time - dob.time)%86400000/3600000
        val minutes = (today.time - dob.time)%86400000%3600000/60000
        val seconds = (today.time - dob.time)%86400000%3600000%60000/1000
        val millisecond = (today.time - dob.time)%86400000%3600000%60000%1000%1000

        textView.text = "Siz yashagansiz\n $week hafta\n $days kun\n $hours soat\n $minutes daqiqa\n $seconds soniya\n $millisecond millsoniya\n"
    }

    fun openDatePicker(view: View) {
        var c = Calendar.getInstance()
        DatePickerDialog(this, { view, year, month, dayOfMonth ->
            var dt = "$dayOfMonth/${month + 1}/$year"
            TimePickerDialog(this, { view, hourOfDay, minute ->
                dt += " $hourOfDay:$minute"
                editTextTextPersonName.setText(dt)
            }, c.get(Calendar.HOUR), c.get(Calendar.MINUTE), false).show()
        }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH)).show()
    }

}