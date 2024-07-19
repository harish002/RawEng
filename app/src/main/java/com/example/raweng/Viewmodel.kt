package com.example.raweng

import android.annotation.SuppressLint
import java.text.ParseException
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale
import java.util.TimeZone
import java.util.Timer
import java.util.TimerTask
import kotlin.concurrent.timer

val id ="1610612748"//MIA
// val id ="1610612738"//BOS

class viewmodelRaw{

    //For Sorting by LocalTime
    @SuppressLint("NewApi")
    private val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())

        @SuppressLint("NewApi")
        fun parseDateTime(dateString: String): LocalDateTime {
            return LocalDateTime.parse(dateString, formatter)
        }

        //Month for sticky Header
        fun getMonthFromDateString(dateString: String): String {
            val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
            dateFormat.timeZone = TimeZone.getTimeZone("UTC")

            return try {
                val date = dateFormat.parse(dateString)
                val monthFormat = SimpleDateFormat("MMMM", Locale.getDefault())
                val year = SimpleDateFormat("yyyy", Locale.getDefault()).format(date)

                monthFormat.format(date) +"  $year"
            } catch (e: ParseException) {
                "N/A"
            }
        }

        //GET DEVICE LOCAL TIME
        fun getLocalDateTimeString(): String {
            val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
            dateFormat.timeZone = TimeZone.getDefault()
            val currentDate = Date()
            return dateFormat.format(currentDate)
        }

        //COMPARE DATE WITH LOCAL TIME FOR GAME EVALUATE
        @SuppressLint("SuspiciousIndentation")
        fun compareDates(dateString1: String, dateString2: String): Int {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
        dateFormat.timeZone = TimeZone.getTimeZone("UTC")

        val date1 = dateFormat.parse(dateString1)
        val date2 = dateFormat.parse(dateString2)
        val date2WithDiff = Date(date2.time + (3 * 60 * 60 * 1000))
            if (date1.after(date2WithDiff) && date1.before(date2)) {
                return 0
            }
        return date1.compareTo(date2)
        }

        //FOR TIME IN SCHEDULE CARD
        fun getTimeFromDateString(dateString: String): String {
            val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
            dateFormat.timeZone = TimeZone.getTimeZone("UTC")

            val date = dateFormat.parse(dateString)
            val timeFormat = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
            return timeFormat.format(date)
        }

        //FOR SCHEDULE CARD STRINGS
         fun formatMonthDayYear(monthDayYear: String): String {
            val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val date = dateFormat.parse(monthDayYear)
            val dayOfWeek = SimpleDateFormat("EEE", Locale.getDefault()).format(date!!)
            val month = SimpleDateFormat("MMM", Locale.getDefault()).format(date)
            val day = SimpleDateFormat("d", Locale.getDefault()).format(date)
            return "${dayOfWeek.uppercase()} ${month.uppercase()} $day"
        }

    fun increaseTimeEveryTenSeconds() {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
        dateFormat.timeZone = TimeZone.getDefault()

        var date = Date()
        val timeFormat = SimpleDateFormat("HH:mm:ss", Locale.getDefault())

        val timer = timer(period = 10000) {
            date = Date(date.time + 60000) // Increase time by 1 minute
            println(timeFormat.format(date))
        }
    }
}

