package com.astar.busschedule.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.astar.busschedule.data.BusScheduleTime
import com.astar.busschedule.data.TimeStorage
import com.astar.busschedule.databinding.FragmentBusScheduleBinding
import com.astar.busschedule.ui.adapter.BusScheduleAdapter

class BusScheduleFragment : Fragment() {

    private var _binding: FragmentBusScheduleBinding? = null
    private val binding: FragmentBusScheduleBinding get() = _binding!!

    private val busScheduleAdapter = BusScheduleAdapter()
    private lateinit var timeStorage: TimeStorage

    private var destination = "home"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setFragmentResultListener(TimeDialog.REQ_CODE_CHANGE_TIME, onChangeTime)

        arguments?.let {
            destination = it.getString("destination", "home")
        }

        timeStorage = TimeStorage.Base(requireContext(), destination)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBusScheduleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupToolbar()
        setupRecycler()
        setupFabButton()
    }

    override fun onStart() {
        super.onStart()
        updateTimeList()
    }

    private fun setupToolbar() = with(binding.toolbar) {
        when(destination) {
            "home" -> title = "Автобусы до дома"
            "work" -> title = "Автобусы до работы"
        }
        setNavigationOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun setupRecycler() = with(binding.recyclerBusScheduler) {
        layoutManager = LinearLayoutManager(requireContext())
        adapter = busScheduleAdapter
        addItemDecoration(DividerItemDecoration(requireContext(), RecyclerView.VERTICAL))
    }

    private fun setupFabButton() = with(binding) {
        fabAddNewItem.setOnClickListener {
            openTimeDialog()
        }
    }

    private fun openTimeDialog() {
        val dialog = TimeDialog.newInstance()
        dialog.show(parentFragmentManager, "time_picker")
    }

    private val onChangeTime: ((String, Bundle) -> Unit) = { _, bundle ->
        val selectedTime = bundle.getParcelable<BusScheduleTime>(TimeDialog.KEY_CHANGE_TIME)
        selectedTime?.let {
            timeStorage.saveTime(selectedTime)
            updateTimeList()
        }
    }

    private fun updateTimeList() {
        val times = timeStorage.fetchTimes().sortedBy { it.hours }
        busScheduleAdapter.setItems(times)
    }

    companion object {
    }
}