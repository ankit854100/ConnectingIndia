package com.leakyquill.bb84.Adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.leakyquill.bb84.Fragments.CommunitiesFragment
import com.leakyquill.bb84.Fragments.GamesFragment
import com.leakyquill.bb84.Fragments.StoriesFragment

class MyPagerAdapter (fm: FragmentManager) : FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                StoriesFragment()
            }
            1 -> {
                CommunitiesFragment()
            }
            else -> {
                return GamesFragment()
            }
        }
    }

    override fun getCount(): Int {
        return 3
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0-> "Stories"
            1-> "Communities"
            else ->{
                "Games"
            }
        }
    }

}