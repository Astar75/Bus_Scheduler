package com.astar.busschedule.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.astar.busschedule.data.BusScheduleTime
import com.astar.busschedule.databinding.FragmentBusScheduleBinding
import com.astar.busschedule.ui.adapter.BusScheduleAdapter

class BusScheduleFragment : Fragment() {

    private var _binding : FragmentBusScheduleBinding? = null
    private val binding : FragmentBusScheduleBinding get() = _binding!!

    private val busScheduleAdapter = BusScheduleAdapter()

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

    private fun setupToolbar() = with(binding.toolbar) {
        setNavigationOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun setupRecycler() = with(binding.recyclerBusScheduler) {
        layoutManager = LinearLayoutManager(requireContext())
        adapter = busScheduleAdapter
        addItemDecoration(DividerItemDecoration(requireContext(), RecyclerView.VERTICAL))

        busScheduleAdapter.setItems(
            listOf(
                BusScheduleTime(System.currentTimeMillis(), "15:39"),
                BusScheduleTime(System.currentTimeMillis(), "16:39"),
                BusScheduleTime(System.currentTimeMillis(), "17:39"),
                BusScheduleTime(System.currentTimeMillis(), "18:39"),
                BusScheduleTime(System.currentTimeMillis(), "19:39"),
                BusScheduleTime(System.currentTimeMillis(), "20:39"),
                BusScheduleTime(System.currentTimeMillis(), "21:39"),
                BusScheduleTime(System.currentTimeMillis(), "22:39")
            )
        )
    }

    private fun setupFabButton() = with(binding) {
        fabAddNewItem.setOnClickListener {

        }
    }

    companion object {
    }
}