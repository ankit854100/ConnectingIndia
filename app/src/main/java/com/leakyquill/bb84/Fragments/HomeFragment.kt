package com.leakyquill.bb84.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.leakyquill.bb84.Adapter.HomeViewPagerAdapter
import com.leakyquill.bb84.R
import com.google.android.material.tabs.TabLayout


class HomeFragment : Fragment() {

    private lateinit var toolbar: Toolbar

    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager
    private lateinit var userPagerAdapter : HomeViewPagerAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val homeFragmentView : View = inflater.inflate(R.layout.fragment_home, container, false)

        toolbar = homeFragmentView.findViewById(R.id.toolbar) as Toolbar
        tabLayout = homeFragmentView.findViewById(R.id.tabLayout)
        viewPager = homeFragmentView.findViewById(R.id.viewPager)

        userPagerAdapter = HomeViewPagerAdapter(childFragmentManager)

        viewPager.adapter = userPagerAdapter
        viewPager.offscreenPageLimit = 6

        tabLayout.setupWithViewPager(viewPager)

        return homeFragmentView
    }


}
