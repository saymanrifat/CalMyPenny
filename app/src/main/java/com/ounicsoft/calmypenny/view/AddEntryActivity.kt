package com.ounicsoft.calmypenny.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ounicsoft.calmypenny.databinding.ActivityAddEntryBinding

class AddEntryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddEntryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddEntryBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}