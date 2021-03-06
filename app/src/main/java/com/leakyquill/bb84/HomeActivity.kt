package com.leakyquill.bb84

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.leakyquill.bb84.Fragments.*
import com.google.firebase.auth.FirebaseAuth
import com.leakyquill.bb84.UserPostActivites.CameraPostActivity
import com.leakyquill.bb84.UserPostActivites.FilesPostActivity
import kotlinx.android.synthetic.main.activity_home.*

//original version

class HomeActivity : AppCompatActivity(), ModelButtomSheetClass.BottomSheetListener {

    lateinit var mAuth : FirebaseAuth
    private lateinit var typeIntent : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        mAuth = FirebaseAuth.getInstance()


        val homeFragment = HomeFragment()
        val discoverFragment = DiscoverFragment()
//        var postFragment = PostFragment()
        val communityFragment = CommunityFragment()
        val notificationFragment = NotificationFragment()

//        homeFragment.view?.setBackgroundResource(R.drawable.home_fragment_selector)

        bottomNavigation.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.nav_home -> makeCurrentFragment(homeFragment)
                R.id.nav_discover -> {
                    makeCurrentFragment(discoverFragment)
                }
                R.id.nav_post -> {

                    val buttomSheetClass = ModelButtomSheetClass()
                    buttomSheetClass.show(supportFragmentManager, "MainActivity")

                }
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

    override fun onButtonClicked(s: String) {
        if (s.equals("camera")){
            startActivity(Intent(this@HomeActivity, CameraPostActivity::class.java))
        }
        else if(s.equals("files")){
            startActivity(Intent(this@HomeActivity, FilesPostActivity::class.java))
        }
        else{
            Toast.makeText(applicationContext, s + " fragment", Toast.LENGTH_SHORT).show()
        }
    }

}
