package com.astar.busschedule.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.astar.busschedule.R
import com.astar.busschedule.databinding.FragmentRoutesBinding


class RoutesFragment : Fragment() {

    private var _binding : FragmentRoutesBinding? = null
    private val binding : FragmentRoutesBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRoutesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null   // обнуляем что бы избежать утечек памяти
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupButtonsNavigate()
    }

    private fun setupButtonsNavigate() = with(binding) {
        buttonHomeRoute.setOnClickListener(onClickListener)
        buttonWorkRoute.setOnClickListener(onClickListener)
    }

    private val onClickListener = View.OnClickListener { view ->
        val destination = when(view) {
            binding.buttonHomeRoute -> "home"
            binding.buttonWorkRoute -> "work"
            else -> "home"
        }
        val bundle = bundleOf("destination" to destination)
        findNavController().navigate(R.id.action_routesFragment_to_busScheduleFragment, bundle)

    }
}