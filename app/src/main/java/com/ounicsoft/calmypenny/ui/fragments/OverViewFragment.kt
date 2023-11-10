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
            PieChart.Slice(
                0.30f,
                Color.rgb(120, 181, 0),
                Color.rgb(149, 224, 0),
                legend = "Food"
            ),
            PieChart.Slice(
                0.20f,
                Color.rgb(204, 168, 0),
                Color.rgb(249, 228, 0),
                legend = "Transportation"
            ),

            PieChart.Slice(
                0.17f,
                Color.rgb(255, 4, 4),
                Color.rgb(255, 72, 86),
                legend = "Entertainment"
            ),
            PieChart.Slice(
                0.20f,
                Color.rgb(0, 162, 216),
                Color.rgb(31, 199, 255),
                legend = "Others"
            ),
            PieChart.Slice(
                0.13f,
                Color.rgb(160, 165, 170),
                Color.rgb(175, 180, 185),
                legend = "Available"
            ),

            )
    }

}
