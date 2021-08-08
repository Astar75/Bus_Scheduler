package com.astar.busschedule.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.astar.busschedule.R
import com.astar.busschedule.databinding.FragmentBusScheduleBinding

class BusScheduleFragment : Fragment() {

    private var _binding : FragmentBusScheduleBinding? = null
    private val binding : FragmentBusScheduleBinding get() = _binding!!

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

    }

    private fun setupToolbar() = with(binding.toolbar) {
        setNavigationOnClickListener {
            findNavController().navigateUp()
        }
    }

    companion object {
    }
}