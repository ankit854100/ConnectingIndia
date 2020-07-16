package com.leakyquill.bb84.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.leakyquill.bb84.CommunityTabs.CommunityItemSelectedActivity
import com.leakyquill.bb84.Model.Community
import com.leakyquill.bb84.R

class CommunityItemSelectedAdapter(private var feeds : List<Community> = emptyList()) : RecyclerView.Adapter<CommunityItemSelectedAdapter.ViewHolder>(){

    private lateinit var mContext: Context
    private lateinit var intent: Intent

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        mContext = parent.context
        return ViewHolder(inflater.inflate(R.layout.fragment_community_item, parent, false))
    }

    override fun getItemCount(): Int {
        return feeds.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        var feed = feeds[position]

        holder.displayPicture.setImageResource(R.drawable.sample1)
        holder.communityName.text = feed.communityName
        holder.mutualFriends.text = feed.mutualFriends.toString()
        holder.followers.text = feed.followers.toString()

        holder.itemView.setOnClickListener {
            intent = Intent(mContext, CommunityItemSelectedActivity::class.java)
            mContext.startActivity(intent)
        }

        holder.joinButton.setOnClickListener {
            Toast.makeText(mContext, "Join Button is clicked", Toast.LENGTH_SHORT).show()
        }
    }

    class ViewHolder(view : View) : RecyclerView.ViewHolder(view){

        val displayPicture : ImageView = view.findViewById(R.id.community_display_picture)
        val communityName : TextView = view.findViewById(R.id.community_name)
        val mutualFriends : TextView = view.findViewById(R.id.mutual_friend_count)
        val followers : TextView = view.findViewById(R.id.member_count)
        val joinButton : Button = view.findViewById(R.id.join)
    }
}