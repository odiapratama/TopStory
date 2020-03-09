package com.codex.topstory.utils

import android.os.Build
import android.text.Html
import android.text.format.DateFormat
import android.widget.TextView
import java.util.*

@Suppress("DEPRECATION")
fun TextView?.setTextHtml(content: String?) = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
    this?.text = Html.fromHtml(content ?: "", Html.FROM_HTML_MODE_COMPACT)
} else {
    this?.text = Html.fromHtml(content ?: "")
}

fun Long?.timeToDate(): String {
    val cal = Calendar.getInstance()
    cal.timeInMillis = (this ?: 0L) * 1000L
    return DateFormat.format("dd/MM/yyyy", cal).toString();
}