package com.leakyquill.bb84.Adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.leakyquill.bb84.CommunityTabs.ChatRoomFragment
import com.leakyquill.bb84.CommunityTabs.FeedFragment
import com.leakyquill.bb84.Fragments.CommunitiesFragment
import com.leakyquill.bb84.Fragments.GamesFragment
import com.leakyquill.bb84.Fragments.StoriesFragment

class CommunityTabAdapter(fm : FragmentManager) : FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                FeedFragment()
            }
            else -> {
                return ChatRoomFragment()
            }
        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0-> "feeds"
            else->{
                "chat room"
            }
        }
    }
}