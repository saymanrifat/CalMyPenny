package com.ounicsoft.calmypenny.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ounicsoft.calmypenny.R
import com.ounicsoft.calmypenny.databinding.ActivityLoginBinding
import com.ounicsoft.calmypenny.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}