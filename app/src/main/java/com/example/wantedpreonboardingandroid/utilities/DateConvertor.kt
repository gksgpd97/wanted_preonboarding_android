package com.example.wantedpreonboardingandroid.utilities

import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

class DateConvertor {
    fun dateConvertor(publishedAt: String): String {
        if (publishedAt == "") {
            return ""
        }
        val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.KOREA)
        format.timeZone = TimeZone.getTimeZone("GMT")
        var past = format.parse(publishedAt)
        val now = Date()
        val seconds = TimeUnit.MILLISECONDS.toSeconds(now.time - past.time)
        val minutes = TimeUnit.MILLISECONDS.toMinutes(now.time - past.time)
        val hours = TimeUnit.MILLISECONDS.toHours(now.time - past.time)
        val days = TimeUnit.MILLISECONDS.toDays(now.time - past.time)

        return when {
            seconds < 60 -> {
                "${seconds}초 전"
            }
            minutes < 60 -> {
                "${minutes}분 전"
            }
            hours < 24 -> {
                "${hours}시간 전"
            }
            else -> {
                "${days}일 전"
            }
        }
    }
}