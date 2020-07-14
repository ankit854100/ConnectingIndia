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
import com.leakyquill.bb84.Model.Feeds

import com.leakyquill.bb84.R


class CommunityFragment : Fragment() {

    private val feedsAdapter : CommunityItemSelectedAdapter by lazy { CommunityItemSelectedAdapter(getFeeds()) }
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
        feedsRecyclerView.adapter = feedsAdapter

        return view
    }

    fun getFeeds(): ArrayList<Feeds> {

        var feedList = ArrayList<Feeds>()

        feedList.add(Feeds(displayPicture = "",communityName = "python", mutualFriends = 12, followers = 120))
        feedList.add(Feeds(displayPicture = "",communityName = "HummingBird", mutualFriends = 10, followers = 680))
        feedList.add(Feeds(displayPicture = "",communityName = "ruby", mutualFriends = 10, followers = 300))
        feedList.add(Feeds(displayPicture = "",communityName = "kotlin", mutualFriends = 0, followers = 20))
        feedList.add(Feeds(displayPicture = "",communityName = "android", mutualFriends = 13, followers = 420))
        feedList.add(Feeds(displayPicture = "",communityName = "java", mutualFriends = 18, followers = 220))
        feedList.add(Feeds(displayPicture = "",communityName = "Title", mutualFriends = 1, followers = 420))
        feedList.add(Feeds(displayPicture = "",communityName = "c++", mutualFriends = 19, followers = 120))

        return feedList
    }

}
