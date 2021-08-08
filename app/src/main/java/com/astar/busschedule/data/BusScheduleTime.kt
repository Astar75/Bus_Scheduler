package com.astar.busschedule.data

import android.os.Parcel
import android.os.Parcelable

data class BusScheduleTime(
    val hours: Int,
    val minutes: Int
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(hours)
        parcel.writeInt(minutes)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<BusScheduleTime> {
        override fun createFromParcel(parcel: Parcel): BusScheduleTime {
            return BusScheduleTime(parcel)
        }

        override fun newArray(size: Int): Array<BusScheduleTime?> {
            return arrayOfNulls(size)
        }
    }
}