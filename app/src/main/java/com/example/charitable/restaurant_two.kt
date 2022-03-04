package com.example.charitable

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.etebarian.meowbottomnavigation.MeowBottomNavigation
import kotlinx.android.synthetic.main.activity_restaurant_two.*
//import meow.bottomnavigation.MeowBottomNavigation
//import meow.bottomnavigation.MeowBottomNavigationCell

class restaurant_two : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addFragment(HomeFragment.newInstance())
        setContentView(R.layout.activity_restaurant_two)
        restaurant_bottom_nav.add(MeowBottomNavigation.Model(0,R.drawable.ic_home_))
        restaurant_bottom_nav.add(MeowBottomNavigation.Model(1,R.drawable.ic_history))
        restaurant_bottom_nav.add(MeowBottomNavigation.Model(2,R.drawable.ic_info))
        restaurant_bottom_nav.add(MeowBottomNavigation.Model(3,R.drawable.ic_person))

        restaurant_bottom_nav.setOnClickMenuListener {
            when(it.id){
                0 -> {
                    replaceFragment(HomeFragment.newInstance())
                }
                1 -> {
                    replaceFragment(HistoryFragment.newInstance())

                }
                2 -> {
                    replaceFragment(ItemFragment.newInstance())

                }
                3 -> {
                    replaceFragment(ProfileFragment.newInstance())

                }
                else -> {
                    replaceFragment(HomeFragment.newInstance(),)


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


