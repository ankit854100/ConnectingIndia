package com.leakyquill.bb84.Adapter

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.ui.SimpleExoPlayerView
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory
import com.leakyquill.bb84.Model.Comments
import com.leakyquill.bb84.Model.Spot
import com.leakyquill.bb84.R

class CardStackVideoAdapter(private var spots : List<Spot> = emptyList())
    : RecyclerView.Adapter<CardStackVideoAdapter.ViewHolder>(){

    private lateinit var mContext: Context
    var bandwidthMeter  = DefaultBandwidthMeter()
    var trackSelector = DefaultTrackSelector(AdaptiveTrackSelection.Factory(bandwidthMeter))
    private lateinit var simpleExoPlayer : SimpleExoPlayer

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        mContext = parent.context
        simpleExoPlayer = ExoPlayerFactory.newSimpleInstance(mContext, trackSelector)

        return ViewHolder(inflater.inflate(R.layout.card_stack_video_adapter_item, parent, false))
    }

    @SuppressLint("WrongConstant")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var spot = spots[position]

        holder.name.text = spot.name
        holder.city.text = spot.city

        var uri = Uri.parse(spot.video)

        holder.simpleExoPlayerView.visibility = View.VISIBLE
        var dataSourceFactory = DefaultHttpDataSourceFactory("exoplayer_video")
        var extractorFactory = DefaultExtractorsFactory()
        var mediaSource = ExtractorMediaSource(uri, dataSourceFactory, extractorFactory, null, null)
        holder.simpleExoPlayerView.player = simpleExoPlayer
        simpleExoPlayer.prepare(mediaSource)


        var commentAdapter = CommentAdapter(getComments())
        holder.recyclerView.layoutManager = LinearLayoutManager(mContext, LinearLayout.VERTICAL , false)
        holder.recyclerView.adapter = commentAdapter
    }

    override fun getItemCount(): Int {
        return spots.size
    }

    fun setSpots(spots: List<Spot>) {
        this.spots = spots
    }

    fun getSpots(): List<Spot> {
        return spots
    }

    fun getComments(): List<Comments> {
        val comments = ArrayList<Comments>()

        comments.add(Comments(userComment = "hey there how are you"))
        comments.add(Comments(userComment = "how you doing"))
        comments.add(Comments(userComment = "get some space bro"))
        comments.add(Comments(userComment = "hey there how are you"))
        comments.add(Comments(userComment = "how you doing"))
        comments.add(Comments(userComment = "get some space bro"))
        comments.add(Comments(userComment = "hey there how are you"))
        comments.add(Comments(userComment = "how you doing"))
        comments.add(Comments(userComment = "get some space bro"))

        return comments
    }

    class ViewHolder(view : View) : RecyclerView.ViewHolder(view){

        val name: TextView = view.findViewById(R.id.item_name)
        var city: TextView = view.findViewById(R.id.item_city)
        var simpleExoPlayerView : SimpleExoPlayerView = view.findViewById(R.id.simpleExoplayerView)
        var recyclerView : RecyclerView = view.findViewById(R.id.commentRecyclerView)
    }
}