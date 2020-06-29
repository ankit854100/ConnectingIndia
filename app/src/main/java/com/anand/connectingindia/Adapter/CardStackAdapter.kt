package com.anand.connectingindia.Adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.anand.connectingindia.Model.Spot
import com.anand.connectingindia.R
import com.bumptech.glide.Glide

class CardStackAdapter(
    private var spots: List<Spot> = emptyList()
) : RecyclerView.Adapter<CardStackAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(inflater.inflate(R.layout.item_card, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val spot = spots[position]
//        holder.name.text = "${spot.id}. ${spot.name}"
//        holder.city.text = spot.city
//        Glide.with(holder.image)
//            .load(spot.url)
//            .into(holder.image)
//
        if (spot.url.isNotEmpty()){
            holder.name.text = "${spot.id}. ${spot.name}"
            holder.city.text = spot.city
            Glide.with(holder.image)
            .load(spot.url)
            .into(holder.image)
        }
        if (spot.video.isNotEmpty()){
            holder.videoView.visibility = View.VISIBLE
            holder.videoView.setVideoURI(Uri.parse(spot.video))
            holder.videoView.start()
        }
        if (spot.postText.isNotEmpty()){
            holder.name.text = "${spot.id}. ${spot.name}"
            holder.city.text = spot.city
            holder.image.visibility = View.GONE
            holder.postLayout.visibility = View.VISIBLE
            holder.post.text = spot.postText
        }



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

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.item_name)
        var city: TextView = view.findViewById(R.id.item_city)
        var image: ImageView = view.findViewById(R.id.item_image)
        var post : TextView = view.findViewById(R.id.postText)
        var postLayout : RelativeLayout = view.findViewById(R.id.postLayout)
        var videoView : VideoView = view.findViewById(R.id.video_view)
    }

}