package com.leakyquill.bb84.Adapter

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Context
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.leakyquill.bb84.Model.Comments
import com.leakyquill.bb84.Model.Spot
import com.leakyquill.bb84.R
import com.borjabravo.readmoretextview.ReadMoreTextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.ui.SimpleExoPlayerView
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory
import com.yuyakaido.android.cardstackview.CardStackListener
import com.yuyakaido.android.cardstackview.Direction

class CardStackAdapter(
    private var spots: List<Spot> = emptyList()
) : RecyclerView.Adapter<CardStackAdapter.ViewHolder>(){

    private lateinit var mContext: Context
    var bandwidthMeter  = DefaultBandwidthMeter()
    var trackSelector = DefaultTrackSelector(AdaptiveTrackSelection.Factory(bandwidthMeter))
    private lateinit var simpleExoPlayer : SimpleExoPlayer

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        mContext = parent.context
        simpleExoPlayer =ExoPlayerFactory.newSimpleInstance(mContext, trackSelector)

        return ViewHolder(inflater.inflate(R.layout.item_card, parent, false))
    }

    @SuppressLint("WrongConstant")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val spot = spots[position]

        holder.scrollView.scrollTo(0,0)

        holder.name.text = spot.name
        holder.city.text = spot.city

        if (spot.postText.equals("") && spot.video.equals("")){

            holder.textPostLayout.visibility = View.GONE
            holder.simpleExoPLayerView.visibility = View.GONE
            holder.videoContainer.visibility = View.GONE
            holder.imageContainer.visibility = View.VISIBLE

            Glide.with(holder.image)
                .load(spot.url)
                .transform(CenterCrop(), RoundedCorners(20))
                .into(holder.image)

        }

        else if (spot.url.equals("") && spot.postText.equals("")){

            holder.imageContainer.visibility = View.GONE
            holder.textPostLayout.visibility = View.GONE
//            holder.simpleExoPLayerView.visibility = View.VISIBLE
            holder.videoContainer.visibility = View.VISIBLE

            holder.videoURL.text = spot.video

            var uri = Uri.parse(spot.video)
            holder.videoContainer.setVideoURI(uri)

//            holder.videoContainer.setVideoPath(spot.video)

//            holder.videoContainer.addOnAttachStateChangeListener(object : View.OnAttachStateChangeListener{
//                override fun onViewDetachedFromWindow(v: View?) {
//                    holder.videoContainer.stopPlayback()
//                    Log.i("onDetach---->", " workring")
//                }
//
//                override fun onViewAttachedToWindow(v: View?) {
//                    holder.videoContainer.setVideoURI(uri)
//                    holder.videoContainer.setOnPreparedListener {
//                        it.start()
//                    }
//                }

//            })

//            holder.simpleExoPLayerView.visibility = View.VISIBLE
//            var dataSourceFactory = DefaultHttpDataSourceFactory("exoplayer_video")
//            var extractorFactory = DefaultExtractorsFactory()
//            var mediaSource = ExtractorMediaSource(uri, dataSourceFactory, extractorFactory, null, null)
//            holder.simpleExoPLayerView.player = simpleExoPlayer
//            simpleExoPlayer.prepare(mediaSource)
////            simpleExoPlayer.playWhenReady = true
        }
        else {
            holder.scrollView.scrollTo(0,0)

            holder.imageContainer.visibility = View.GONE
            holder.simpleExoPLayerView.visibility = View.GONE
            holder.videoContainer.visibility = View.GONE
            holder.textPostLayout.visibility = View.VISIBLE

            holder.name.text = "${spot.id}. ${spot.name}"
            holder.city.text = spot.city
            holder.readMoreText.text = spot.postText
        }


        var commentAdapter = CommentAdapter(getComments())
        holder.recyclerView.layoutManager = LinearLayoutManager(mContext, LinearLayout.VERTICAL , false)
        holder.recyclerView.adapter = commentAdapter



        holder.itemView.setOnClickListener { v ->
            Toast.makeText(v.context, spot.name, Toast.LENGTH_SHORT).show()
        }
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


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.item_name)
        var city: TextView = view.findViewById(R.id.item_city)
        var image: ImageView = view.findViewById(R.id.item_image)
        var imageContainer : LinearLayout = view.findViewById(R.id.image_container)
        var recyclerView : RecyclerView = view.findViewById(R.id.commentRecyclerView)
        var scrollView : NestedScrollView = view.findViewById(R.id.scrollView)
        var readMoreText : ReadMoreTextView = view.findViewById(R.id.read_more_text)
        var textPostLayout : RelativeLayout = view.findViewById(R.id.text_post_layout)
        var videoContainer : VideoView = view.findViewById(R.id.video_view)
        var simpleExoPLayerView : SimpleExoPlayerView = view.findViewById(R.id.simpleExoplayerView)
        var videoURL : TextView = view.findViewById(R.id.video_url)


    }


}