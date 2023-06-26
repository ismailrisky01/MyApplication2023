package com.ismaildev.myapplication2023.util

import android.os.Build
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class DateHelper {
    fun getCurrentYear(): String? {
        val calendar = Calendar.getInstance()
        return calendar[Calendar.YEAR].toString()
    }
    fun getCurrentDate():String{
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val current = LocalDateTime.now()
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")
            current.format(formatter)
        } else {
            val dateNow = Calendar.getInstance().time
            val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
            sdf.format(dateNow)
        }

    }
}