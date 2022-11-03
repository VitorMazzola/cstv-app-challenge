package com.challenge.cstvapp.extensions

import android.text.format.DateUtils
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*

fun Date.toLocalDateTime(): LocalDateTime {
    return toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()
}

fun LocalDateTime.getStatusLabel(): String {
    return if(this.isToday()) {
        this.toToday()
    } else if(this.isTomorrow()) {
        this.toWeekDayHour()
    } else {
        this.toDateAndHour()
    }
}

private fun LocalDateTime.toToday() = DateTimeFormatter.ofPattern("'Hoje', HH:mm").format(this)

private fun LocalDateTime.toWeekDayHour() = DateTimeFormatter.ofPattern("EEE, HH:mm").format(this)

private fun LocalDateTime.toDateAndHour() = DateTimeFormatter.ofPattern("dd.MM HH:mm").format(this)

private fun LocalDateTime.isToday() = DateUtils.isToday(this.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli())

private fun LocalDateTime.isTomorrow() : Boolean {
    val calendar = Calendar.getInstance()
    calendar.add(Calendar.DAY_OF_YEAR, 1)

    val tomorrow = DateTimeFormatter.ofPattern("dd/MM/yyyy").format(calendar.time.toLocalDateTime())
    val date = DateTimeFormatter.ofPattern("dd/MM/yyyy").format(this)

    return tomorrow == date
}