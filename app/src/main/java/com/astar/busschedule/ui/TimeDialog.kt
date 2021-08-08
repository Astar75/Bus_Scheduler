package com.astar.busschedule.ui

import android.app.Dialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.text.format.DateFormat
import android.widget.TimePicker
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.setFragmentResult
import com.astar.busschedule.data.BusScheduleTime
import java.util.*

class TimeDialog : DialogFragment(), TimePickerDialog.OnTimeSetListener {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)

        return TimePickerDialog(
            requireContext(),
            this,
            hour,
            minute,
            !DateFormat.is24HourFormat(requireContext())
        )
    }

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        setFragmentResult(REQ_CODE_CHANGE_TIME, bundleOf(
            KEY_CHANGE_TIME to BusScheduleTime(hourOfDay, minute)
        ))
    }

    companion object {
        const val REQ_CODE_CHANGE_TIME = "com.astar.busschedule.REQ_CODE_CHANGE_TIME"
        const val KEY_CHANGE_TIME = "com.astar.busschedule.KEY_CHANGE_TIME"

        fun newInstance() = TimeDialog()
    }
}