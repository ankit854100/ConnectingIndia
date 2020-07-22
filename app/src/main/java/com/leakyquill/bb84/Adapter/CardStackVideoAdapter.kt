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
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.leakyquill.bb84.Model.Comments
import com.leakyquill.bb84.Model.Spot
import com.leakyquill.bb84.R

class CardStackVideoAdapter(private var mContext: Context, private var spots : List<Spot> = emptyList())
    : RecyclerView.Adapter<CardStackVideoAdapter.ViewHolder>(){

    var viewHolder : ViewHolder? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return ViewHolder(inflater.inflate(R.layout.card_stack_video_adapter_item, parent, false))
    }

    @SuppressLint("WrongConstant")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val spot = spots[position]

//        holder.name.text = spot.name
//        holder.city.text = spot.city

        val uri = Uri.parse(spot.video)

        viewHolder = holder

        holder.initializePlayer(mContext, uri)

        val commentAdapter = CommentAdapter(getComments())
        holder.recyclerView.layoutManager = LinearLayoutManager(mContext, LinearLayout.VERTICAL , false)
        holder.recyclerView.adapter = commentAdapter
    }

    override fun onViewAttachedToWindow(holder: ViewHolder) {
        holder.player?.playWhenReady = true
        holder.playWhenReady = true
        super.onViewAttachedToWindow(holder)
    }
    override fun onViewDetachedFromWindow(holder: ViewHolder) {

        holder.releasePlayer()
        super.onViewDetachedFromWindow(holder)
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

        var name: TextView = view.findViewById(R.id.item_name)
        var city: TextView = view.findViewById(R.id.item_city)
        var playerView : PlayerView = view.findViewById(R.id.simpleExoPlayerView)
        var recyclerView : RecyclerView = view.findViewById(R.id.commentRecyclerView)
        var play : View = view.findViewById(R.id.play_view)
        var playWhenReady = false

        var player : SimpleExoPlayer? = null

        fun initializePlayer(mContext : Context, uri: Uri){
//            playerView.visibility = View.VISIBLE
            player = SimpleExoPlayer.Builder(mContext).build()
            playerView.player = player

            val mediaSource : MediaSource = this.buildMediaSource(uri, mContext)!!

            player!!.prepare(mediaSource, false, false)
            playWhenReady = true
        }

        fun releasePlayer(){
            player?.playWhenReady = false
            playWhenReady = false
        }

        fun buildMediaSource(uri: Uri, mContext: Context): MediaSource? {
            val dataSourceFactory: DataSource.Factory =
                DefaultDataSourceFactory(mContext, "exoplayer-codelab")
            return ProgressiveMediaSource.Factory(dataSourceFactory).createMediaSource(uri)
        }

    }




}