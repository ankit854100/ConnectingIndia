package com.leakyquill.bb84.CommunityTabs

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.leakyquill.bb84.Adapter.CommunityFeedsAdapter
import com.leakyquill.bb84.Model.Feeds

import com.leakyquill.bb84.R

class FeedFragment : Fragment() {

    private lateinit var feedsAdapter : CommunityFeedsAdapter
    private lateinit var feedsRecyclerView: RecyclerView

    @SuppressLint("WrongConstant")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view : View = inflater.inflate(R.layout.fragment_feeds, container, false)

        feedsRecyclerView = view.findViewById(R.id.community_feeds_recycler_view)

        feedsAdapter = context?.let { CommunityFeedsAdapter(it, getFeeds()) }!!

        feedsRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        feedsRecyclerView.adapter = feedsAdapter

        return view
    }

    private fun getFeeds(): List<Feeds> {

        val feeds = ArrayList<Feeds>()

        feeds.add(Feeds(imageUrl = "https://images.unsplash.com/photo-1528795259021-d8c86e14354c?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=755&q=80", videoUrl = "", postText = "", numberOFLikes = 2))
        feeds.add(Feeds(imageUrl = "https://source.unsplash.com/AWh9C-QjhE4/600x800", videoUrl = "", postText = "", numberOFLikes = 2))
        feeds.add(Feeds(imageUrl = "https://images.unsplash.com/photo-1528795259021-d8c86e14354c?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=755&q=80", videoUrl = "", postText = "", numberOFLikes = 2))
        feeds.add(Feeds(imageUrl = "https://source.unsplash.com/AWh9C-QjhE4/600x800", videoUrl = "", postText = "", numberOFLikes = 2))
        feeds.add(Feeds(imageUrl = "https://images.unsplash.com/photo-1528795259021-d8c86e14354c?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=755&q=80", videoUrl = "", postText = "", numberOFLikes = 2))
        feeds.add(Feeds(imageUrl = "", videoUrl = "", postText = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum. It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for 'lorem ipsum' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like).", numberOFLikes = 2))

        return feeds
    }

}
