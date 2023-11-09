package com.ounicsoft.calmypenny.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.ounicsoft.calmypenny.R
import com.ounicsoft.calmypenny.databinding.ActivityHomeBinding
import com.ounicsoft.calmypenny.view.home.BudgetFragment
import com.ounicsoft.calmypenny.view.home.OverViewFragment
import com.ounicsoft.calmypenny.view.home.WalletFragment

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private var overViewFragment: OverViewFragment = OverViewFragment()
    private var budgetFragment: BudgetFragment = BudgetFragment()
    private var walletFragment: WalletFragment = WalletFragment()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setCurrentFragment(overViewFragment)
        //Handle Bottom Navigation ClickListen
        handleBottomClickListener()
        //Handle Add Entry Click Listen
        goToAddEntryActivity()


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
}