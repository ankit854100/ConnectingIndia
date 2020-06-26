package com.anand.connectingindia.Adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.anand.connectingindia.Fragments.CommunityFragment
import com.anand.connectingindia.Fragments.GamesFragment
import com.anand.connectingindia.Fragments.StoriesFragment

class MyPagerAdapter (fm: FragmentManager) : FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                StoriesFragment()
            }
            1 -> {
                CommunityFragment()
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