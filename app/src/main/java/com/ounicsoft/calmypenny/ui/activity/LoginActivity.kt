package com.ounicsoft.calmypenny.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ounicsoft.calmypenny.R
import com.ounicsoft.calmypenny.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}