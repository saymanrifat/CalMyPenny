package com.ounicsoft.calmypenny.ui.fragments

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ounicsoft.calmypenny.databinding.FragmentOverViewBinding
import ir.mahozad.android.PieChart

class OverViewFragment : Fragment() {
    private lateinit var binding: FragmentOverViewBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOverViewBinding.inflate(layoutInflater, container, false)
        pieChart()
        return binding.root
    }

    private fun pieChart() {
        binding.pieChart.slices = listOf(
            PieChart.Slice(0.2f, Color.BLUE),
            PieChart.Slice(0.4f, Color.MAGENTA),
            PieChart.Slice(0.3f, Color.YELLOW),
            PieChart.Slice(0.1f, Color.CYAN)
        )
    }

}
