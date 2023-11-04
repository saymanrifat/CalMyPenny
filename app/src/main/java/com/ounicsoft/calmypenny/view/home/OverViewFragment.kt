package com.ounicsoft.calmypenny.view.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ounicsoft.calmypenny.databinding.FragmentOverViewBinding

class OverViewFragment : Fragment() {
    private lateinit var binding: FragmentOverViewBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOverViewBinding.inflate(layoutInflater, container, false)

        return binding.root
    }

}