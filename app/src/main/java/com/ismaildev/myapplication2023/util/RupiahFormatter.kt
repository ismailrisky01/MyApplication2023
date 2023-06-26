package com.ismaildev.myapplication2023.util

import android.text.TextWatcher
import android.widget.TextView
import java.util.*

class RupiahFormatter {

    var localeID = Locale("in", "ID")

    fun format(value: Double): String? {
        val format = "%,.0f"
        val format2 = "%,.2f"
        return if (value % 1 == 0.0) {
            "Rp. " + String.format(localeID, format, value)
        } else {
            "Rp. " + String.format(localeID, format2, value)
        }
    }

    fun formatEditText(s: CharSequence, tw: TextWatcher?, tv: TextView): String? {
        var current = ""
        val format = "%,.0f"
        val cleanString = s.toString().replace("[Rp,.]".toRegex(), "")
        var parsed = 0.0
        return if (cleanString != current && !cleanString.isEmpty()) {
            tv.removeTextChangedListener(tw)
            try {
                parsed = cleanString.toDouble()
            } catch (e: Exception) {
            }
            val formatted = "Rp. " + String.format(localeID, format, parsed)
            current = formatted
            current
        } else {
            "Rp. "
        }
    }
}