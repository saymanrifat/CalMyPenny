package com.ounicsoft.calmypenny

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.ounicsoft.calmypenny.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private val overViewFragment = OverViewFragment()
    private val budgetFragment = BudgetFragment()
    private val walletFragment = WalletFragment()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setCurrentFragment(overViewFragment)
        //Handle Bottom Navigation ClickListen
        handleBottomClickListener()


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

    private fun setCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.home_HostFragment, fragment)
            commit()
        }
}