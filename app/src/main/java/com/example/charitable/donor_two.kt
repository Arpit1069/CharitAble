package com.example.charitable

import android.os.Bundle
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.charitable.databinding.ActivityDonorTwoBinding
import kotlinx.android.synthetic.main.activity_donor_two.*
import meow.bottomnavigation.MeowBottomNavigation

class donor_two : BaseActivity() {

    private lateinit var binding: ActivityDonorTwoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_donor_two)
//        binding = ActivityDonorTwoBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        val navView: BottomNavigationView = binding.navView
//
//        val navController = findNavController(R.id.nav_host_fragment_activity_donor_two)
//        // Passing each menu ID as a set of Ids because each
//        // menu should be considered as top level destinations.
//        val appBarConfiguration = AppBarConfiguration(
//            setOf(
//                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
//            )
//        )
//        setupActionBarWithNavController(navController, appBarConfiguration)
//        navView.setupWithNavController(navController)
       bottomNavigation.add(MeowBottomNavigation.Model(0,R.drawable.ic_home))
        bottomNavigation.add(MeowBottomNavigation.Model(1,R.drawable.ic_list))
        bottomNavigation.add(MeowBottomNavigation.Model(2,R.drawable.ic_profile))


        bottomNavigation.setOnClickMenuListener {
            when (it.id) {
                0 -> {
                    Toast.makeText(this@donor_two, "Home Fragment", Toast.LENGTH_SHORT).show()
                }
                1 -> {
                    Toast.makeText(this@donor_two, "List Fragment", Toast.LENGTH_SHORT).show()
                }
                2 -> {
                    Toast.makeText(this@donor_two, "profile Fragment", Toast.LENGTH_SHORT).show()
                }
                else -> {
                    Toast.makeText(this@donor_two,"Home Fragment",Toast.LENGTH_SHORT).show()

                }
            }
        }
    }
}