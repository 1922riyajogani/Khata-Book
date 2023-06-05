package com.example.khata_book

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.example.khata_book.Fragment.BudgetFragment
import com.example.khata_book.Fragment.HomeFragment
import com.example.khata_book.Fragment.MoreFragment
import com.example.khata_book.Fragment.StatsFragment
import com.example.khata_book.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.lang.reflect.Type

class MainActivity : AppCompatActivity() {

    lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var Type = arrayOf("home", "budget", "stats", "more")
        var Fragment = arrayOf(HomeFragment(), BudgetFragment(), StatsFragment(), MoreFragment())
        loadFragment(HomeFragment())
        binding.bnview.setOnNavigationItemSelectedListener(object : BottomNavigationView.OnNavigationItemSelectedListener {
            override fun onNavigationItemSelected(item: MenuItem): Boolean {

                when(item.itemId){
                    R.id.home->{
                        loadFragment(HomeFragment())
                    }
                }
                when (item.itemId) {
                    R.id.budget -> {
                        loadFragment(BudgetFragment())
                    }
                }
                when (item.itemId) {
                    R.id.stats -> {
                        loadFragment(StatsFragment())
                    }
                }
                when (item.itemId) {
                    R.id.more -> {
                        loadFragment(MoreFragment())
                    }
                }
                return true

            }
        })

    }

    private fun loadFragment(fragment: Fragment){
         supportFragmentManager
             .beginTransaction()
             .replace(R.id.fmlayout,fragment)
             .commit()
}

}