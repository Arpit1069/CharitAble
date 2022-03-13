package com.example.charitable

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.etebarian.meowbottomnavigation.MeowBottomNavigation
import com.example.charitable.firebase.FirestoreClass
import com.google.firebase.firestore.auth.User
import kotlinx.android.synthetic.main.activity_donor_two.*
import kotlinx.android.synthetic.main.fragment_profile.*


class donor_two : BaseActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addFragment(HomeFragment_donor.newInstance())
        setContentView(R.layout.activity_donor_two)

        FirestoreClass().loadUserData(this)

        donor_bottom_nav.add(MeowBottomNavigation.Model(0,R.drawable.ic_home_))
        donor_bottom_nav.add(MeowBottomNavigation.Model(1,R.drawable.ic_history))
      //  donor_bottom_nav.add(MeowBottomNavigation.Model(2,R.drawable.ic_info))
        donor_bottom_nav.add(MeowBottomNavigation.Model(2,R.drawable.ic_person))

        donor_bottom_nav.setOnClickMenuListener {
            when(it.id){
                0 -> {
                    replaceFragment(HomeFragment_donor.newInstance())
                }
                1 -> {
                    replaceFragment(HistoryFragment_donor.newInstance())

                }
                2 -> {

//                updateNavigationUserDetails(user = com.example.charitable.models.User())
                    replaceFragment(ProfileFragment_donor.newInstance())
                }
                else -> {
                    replaceFragment(HomeFragment_donor.newInstance(),)

                }
            }
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentTransition = supportFragmentManager.beginTransaction()
        fragmentTransition.replace(R.id.FragmentContainer,fragment).addToBackStack(Fragment::class.java.simpleName).commit()
    }
    private fun addFragment(fragment: Fragment) {
        val fragmentTransition = supportFragmentManager.beginTransaction()
        fragmentTransition.add(R.id.FragmentContainer,fragment).addToBackStack(Fragment::class.java.simpleName).commit()


    }
}