package com.ounicsoft.calmypenny

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ounicsoft.calmypenny.databinding.FragmentDashboardBinding

class DashboardFragment : Fragment() {

    lateinit var binding: FragmentDashboardBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDashboardBinding.inflate(inflater, container, false)


        return binding.root
    }
}