package com.astar.busschedule.data

import android.content.Context

interface TimeStorage {

    fun saveTime(time: BusScheduleTime)

    fun fetchTimes(): List<BusScheduleTime>

    class Base(context: Context, private val destination: String) : TimeStorage {

        private val preferences = context.getSharedPreferences(STORAGE_NAME, Context.MODE_PRIVATE)

        override fun saveTime(time: BusScheduleTime) {
            preferences.edit().putString(
                "${destination}_${time.hours}${time.minutes}",
                "${destination}_${time.hours}:${time.minutes}"
            ).apply()
        }

        override fun fetchTimes(): List<BusScheduleTime> {
            val times =  preferences.all.values.toList()
                .map { it.toString() }
                .filter { it.contains(destination) }
                .map {
                    val source = it
                    val split = source.split(":")
                    val hour = split[0].substring((destination + "_").length, split[0].length).toInt()
                    val minute = split[1].toInt()
                    BusScheduleTime(hour, minute)
                }

            return times
        }

        companion object {
            private const val STORAGE_NAME = "TIME_STORAGE"
        }
    }
}