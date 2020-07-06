package com.leakyquill.bb84.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.leakyquill.bb84.Model.Stories
import com.leakyquill.bb84.R
import com.leakyquill.bb84.StoriesActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners

class StoriesAdapter(private var storiesList: List<Stories> = emptyList()) : RecyclerView.Adapter<StoriesAdapter.ViewHolder>() {

    private lateinit var mContext: Context
    private lateinit var intent: Intent

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        mContext = parent.context
        return ViewHolder(inflater.inflate(R.layout.item_stories, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val storyItem = storiesList[position]

        Glide.with(holder.profileImage)
            .load(storyItem.profileImage)
            .transform(CenterCrop(), RoundedCorners(20))
            .into(holder.profileImage)

        holder.playButton.setOnClickListener {
            Toast.makeText(mContext, "play button is clicked", Toast.LENGTH_SHORT).show()
            intent = Intent(mContext, StoriesActivity::class.java)
            mContext.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return storiesList.size
    }


    class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {

        var profileImage : ImageView = view.findViewById(R.id.stories_image)
        var playButton : ImageView = view.findViewById(R.id.play01)

    }
}