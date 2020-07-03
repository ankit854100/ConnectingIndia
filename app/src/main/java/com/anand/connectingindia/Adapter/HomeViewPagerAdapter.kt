package com.anand.connectingindia.Adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.anand.connectingindia.UserHomeFragment.UserHomeFragment
import com.anand.connectingindia.UserHomeFragment.UserVideosFragment

class HomeViewPagerAdapter(fm : FragmentManager) : FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return when(position){
            0 -> UserHomeFragment()
            else-> {
                UserVideosFragment()
            }
        }
    }

    override fun getCount(): Int {
        return 7
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0-> "home"
            1-> "trending"
            2-> "videos"
            3-> "music"
            4-> "games"
            5-> "liks"
            else->{
                "others"
            }
        }
    }
}