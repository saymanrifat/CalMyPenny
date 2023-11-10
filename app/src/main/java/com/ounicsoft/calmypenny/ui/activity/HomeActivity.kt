package com.ounicsoft.calmypenny.ui.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.ounicsoft.calmypenny.R
import com.ounicsoft.calmypenny.databinding.ActivityHomeBinding
import com.ounicsoft.calmypenny.ui.fragments.BudgetFragment
import com.ounicsoft.calmypenny.ui.fragments.OverViewFragment
import com.ounicsoft.calmypenny.ui.fragments.WalletFragment


class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private var overViewFragment: OverViewFragment = OverViewFragment()
    private var budgetFragment: BudgetFragment = BudgetFragment()
    private var walletFragment: WalletFragment = WalletFragment()
    private lateinit var toast: Toast
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setCurrentFragment(overViewFragment)
        //Handle Bottom Navigation ClickListen
        handleBottomClickListener()
        //Handle Add Entry Click Listen
        goToAddEntryActivity()
        //Handle Menu Click Listen
        onMenuSelected()
        // The Toolbar defined in the layout has the id "my_toolbar".
        setSupportActionBar(binding.homeToolbar)
        //Initialize Toast
        toast = Toast(this)


    }

    private fun handleBottomClickListener() {
        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.menu_overView -> setCurrentFragment(overViewFragment)
                R.id.menu_budget -> setCurrentFragment(budgetFragment)
                R.id.menu_wallet -> setCurrentFragment(walletFragment)
            }
            true
        }
    }

    private fun setCurrentFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.home_HostFragment, fragment)
            commit()
        }
    }

    private fun goToAddEntryActivity() {
        binding.btnAddEntryActivity.setOnClickListener {
            startActivity(Intent(this, AddEntryActivity::class.java))
        }
    }

    private fun onMenuSelected() {
        binding.btnSettings.setOnClickListener {
            showToast("Setting is Ok")
        }
    }

    private fun showToast(text: String) {
        toast.cancel()
        toast = Toast.makeText(this, text, Toast.LENGTH_LONG)
        toast.show()
    }
}