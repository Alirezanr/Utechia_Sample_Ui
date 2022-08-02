package com.example.ui_sample.util

import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.*


fun Double.toPriceFormat(): String =
    DecimalFormat("#,###.##", DecimalFormatSymbols(Locale.ENGLISH)).format(this)