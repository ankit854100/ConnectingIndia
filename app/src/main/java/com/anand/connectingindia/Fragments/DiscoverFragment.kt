package com.anand.connectingindia.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity

import androidx.viewpager.widget.ViewPager
import com.anand.connectingindia.Adapter.MyPagerAdapter

import com.anand.connectingindia.R
import com.google.android.material.tabs.TabLayout


class DiscoverFragment : Fragment() {

    private lateinit var adapter : MyPagerAdapter
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view : View = inflater!!.inflate(R.layout.fragment_discover, container, false)

        tabLayout = view.findViewById(R.id.tabLayout)
        viewPager = view.findViewById(R.id.viewPager)

        adapter = MyPagerAdapter((activity as AppCompatActivity).supportFragmentManager)

        viewPager.adapter = adapter

        tabLayout.setupWithViewPager(viewPager)


        return view
    }

}
