package com.leakyquill.bb84.Fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.leakyquill.bb84.Adapter.CommunityStoriesAdapter
import com.leakyquill.bb84.Adapter.StoriesAdapter
import com.leakyquill.bb84.Model.Stories

import com.leakyquill.bb84.R

class StoriesFragment : Fragment() {

    private lateinit var playImage : ImageView
    private lateinit var playImage1 : ImageView
    private lateinit var intent: Intent

    private val storiesAdapter : StoriesAdapter by lazy { StoriesAdapter(createFollowingStoriesList()) }
    private lateinit var storiesRecyclerView : RecyclerView

    private lateinit var communityStoriesRecyclerView: RecyclerView
    private val communityStoriesAdapter : CommunityStoriesAdapter by lazy { CommunityStoriesAdapter(createCommunityStoriesList()) }


    @SuppressLint("WrongConstant")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view : View = inflater.inflate(R.layout.fragment_stories, container, false)

        storiesRecyclerView = view.findViewById(R.id.stories_recycler_view)
        storiesRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayout.HORIZONTAL, false)
        storiesRecyclerView.adapter = storiesAdapter


        communityStoriesRecyclerView = view.findViewById(R.id.community_recycler_view_stories)
        communityStoriesRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayout.HORIZONTAL, false)
        communityStoriesRecyclerView.adapter = communityStoriesAdapter


        return view
    }

    private fun createFollowingStoriesList() : List<Stories> {
        val story = ArrayList<Stories>()

        story.add( Stories(name = "monica", profileImage = "https://source.unsplash.com/CdVAUADdqEc/600x800" ))
        story.add( Stories(name = "chandler", profileImage = "https://source.unsplash.com/Xq1ntWruZQI/600x800" ))
        story.add( Stories(name = "rachel", profileImage = "https://source.unsplash.com/LrMWHKqilUw/600x800" ))
        story.add( Stories(name = "monica", profileImage = "https://source.unsplash.com/CdVAUADdqEc/600x800" ))
        story.add( Stories(name = "chandler", profileImage = "https://source.unsplash.com/Xq1ntWruZQI/600x800" ))
        story.add( Stories(name = "rachel", profileImage = "https://source.unsplash.com/LrMWHKqilUw/600x800" ))

        return story
    }

    private fun createCommunityStoriesList() : List<Stories> {
        val story = ArrayList<Stories>()

        story.add( Stories(name = "monica", profileImage = "https://source.unsplash.com/Xq1ntWruZQI/600x800" ))
        story.add( Stories(name = "chandler", profileImage = "https://source.unsplash.com/Xq1ntWruZQI/600x800" ))
        story.add( Stories(name = "rachel", profileImage = "https://source.unsplash.com/LrMWHKqilUw/600x800" ))
        story.add( Stories(name = "monica", profileImage = "https://source.unsplash.com/CdVAUADdqEc/600x800" ))
        story.add( Stories(name = "chandler", profileImage = "https://source.unsplash.com/Xq1ntWruZQI/600x800" ))
        story.add( Stories(name = "rachel", profileImage = "https://source.unsplash.com/CdVAUADdqEc/600x800" ))

        return story
    }

}
