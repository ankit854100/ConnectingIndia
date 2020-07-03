package com.anand.connectingindia

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.anand.connectingindia.Fragments.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_home.*

//original version

class HomeActivity : AppCompatActivity() {

    lateinit var mAuth : FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        mAuth = FirebaseAuth.getInstance()


        var homeFragment = HomeFragment()
        var discoverFragment = DiscoverFragment()
        var postFragment = PostFragment()
        var communityFragment = CommunityFragment()
        var notificationFragment = NotificationFragment()

//        homeFragment.view?.setBackgroundResource(R.drawable.home_fragment_selector)

        bottomNavigation.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.nav_home -> makeCurrentFragment(homeFragment)
                R.id.nav_discover -> makeCurrentFragment(discoverFragment)
                R.id.nav_post -> makeCurrentFragment(postFragment)
                R.id.nav_communities -> makeCurrentFragment(communityFragment)
                R.id.nav_notification -> makeCurrentFragment(notificationFragment)

            }

            true
        }

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragmentContainer, homeFragment)
            commit()
        }

    }


    private fun makeCurrentFragment(Fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragmentContainer, Fragment)
            addToBackStack(null)
            commit()
        }

}
