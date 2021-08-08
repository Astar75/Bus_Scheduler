package com.astar.busschedule.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.astar.busschedule.R
import com.astar.busschedule.data.BusScheduleTime

class BusScheduleAdapter : RecyclerView.Adapter<BusScheduleAdapter.BusSchedulerViewHolder>() {

    private val items: MutableList<BusScheduleTime> = ArrayList()

    fun setItems(newItems: List<BusScheduleTime>) {
        this.items.clear()
        this.items.addAll(newItems)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = BusSchedulerViewHolder(
        LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_bus_schedule, parent, false)
    )

    override fun onBindViewHolder(holder: BusSchedulerViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class BusSchedulerViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val textTimeSchedule: TextView = view.findViewById(R.id.textTimeSchedule)

        fun bind(item: BusScheduleTime) {
            textTimeSchedule.text = String.format("%02d:%02d", item.hours, item.minutes)
        }
    }
}