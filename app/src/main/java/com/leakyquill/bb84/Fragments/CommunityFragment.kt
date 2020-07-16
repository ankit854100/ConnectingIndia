package com.leakyquill.bb84.Fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.leakyquill.bb84.Adapter.CommunityItemSelectedAdapter
import com.leakyquill.bb84.Model.Community

import com.leakyquill.bb84.R


class CommunityFragment : Fragment() {

    private val communityItemSelectedAdapter : CommunityItemSelectedAdapter by lazy { CommunityItemSelectedAdapter(getFeeds()) }
    private lateinit var feedsRecyclerView: RecyclerView

    @SuppressLint("WrongConstant")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view : View = inflater.inflate(R.layout.fragment_community, container, false)

        feedsRecyclerView = view.findViewById(R.id.feeds_recycler_view)
        feedsRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayout.VERTICAL, false)
        feedsRecyclerView.adapter = communityItemSelectedAdapter

        return view
    }

    fun getFeeds(): ArrayList<Community> {

        var feedList = ArrayList<Community>()

        feedList.add(Community(displayPicture = "",communityName = "python", mutualFriends = 12, followers = 120))
        feedList.add(Community(displayPicture = "",communityName = "HummingBird", mutualFriends = 10, followers = 680))
        feedList.add(Community(displayPicture = "",communityName = "ruby", mutualFriends = 10, followers = 300))
        feedList.add(Community(displayPicture = "",communityName = "kotlin", mutualFriends = 0, followers = 20))
        feedList.add(Community(displayPicture = "",communityName = "android", mutualFriends = 13, followers = 420))
        feedList.add(Community(displayPicture = "",communityName = "java", mutualFriends = 18, followers = 220))
        feedList.add(Community(displayPicture = "",communityName = "Title", mutualFriends = 1, followers = 420))
        feedList.add(Community(displayPicture = "",communityName = "c++", mutualFriends = 19, followers = 120))

        return feedList
    }

}
