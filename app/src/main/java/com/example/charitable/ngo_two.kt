package com.example.charitable

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.etebarian.meowbottomnavigation.MeowBottomNavigation
import com.example.charitable.HomeFragment_ngo.Companion.newInstance
import com.example.charitable.InfoFragment_res.Companion.newInstance
import kotlinx.android.synthetic.main.activity_ngo_two.*
import kotlinx.android.synthetic.main.activity_restaurant_two.*

class ngo_two : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addFragment(HomeFragment_ngo.newInstance())
        setContentView(R.layout.activity_ngo_two)

        ngo_bottom_nav.add(MeowBottomNavigation.Model(0,R.drawable.ic_home_))
        ngo_bottom_nav.add(MeowBottomNavigation.Model(1,R.drawable.ic_history))
        ngo_bottom_nav.add(MeowBottomNavigation.Model(2,R.drawable.ic_profile))

        ngo_bottom_nav.setOnClickMenuListener {
            when(it.id){
                0 -> {
                    replaceFragment(HomeFragment_ngo.newInstance())
                }
                1 -> {
                    replaceFragment(HistoryFragment_res.newInstance())
                }
                2 -> {
                    replaceFragment(ProfileFragment_res.newInstance())
                }

                else -> {
                    replaceFragment(HomeFragment_res.newInstance(),)

                }
            }
        }
    }

    private fun replaceFragment(fragment:Fragment) {
        val fragmentTransition = supportFragmentManager.beginTransaction()
        fragmentTransition.replace(R.id.FragmentContainer,fragment).addToBackStack(Fragment::class.java.simpleName).commit()
    }

    private fun addFragment(fragment:Fragment) {
        val fragmentTransition = supportFragmentManager.beginTransaction()
        fragmentTransition.add(R.id.FragmentContainer,fragment).addToBackStack(Fragment::class.java.simpleName).commit()
    }
}

