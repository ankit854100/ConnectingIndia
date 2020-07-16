package com.leakyquill.bb84.CommunityTabs

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.leakyquill.bb84.Adapter.CommunityTabAdapter
import com.leakyquill.bb84.R

class CommunityItemSelectedActivity : AppCompatActivity() {

    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager
    private lateinit var userPagerAdapter : CommunityTabAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_community_item_selected)

        tabLayout = findViewById(R.id.tabLayout)
        viewPager = findViewById(R.id.viewPager)

        userPagerAdapter = CommunityTabAdapter(supportFragmentManager)
        viewPager.adapter = userPagerAdapter

        tabLayout.setupWithViewPager(viewPager)
    }

    override fun onBackPressed() {

        this@CommunityItemSelectedActivity.finish()
        Log.i("CommunityTabSelected: ", "is finished.")

        super.onBackPressed()
    }
}
