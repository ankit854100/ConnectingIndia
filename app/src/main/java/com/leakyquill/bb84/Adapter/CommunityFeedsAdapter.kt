package com.leakyquill.bb84.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.recyclerview.widget.RecyclerView
import com.borjabravo.readmoretextview.ReadMoreTextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.leakyquill.bb84.CommunityTabs.Feeds.FeedsCommentActivity
import com.leakyquill.bb84.Model.Community
import com.leakyquill.bb84.Model.Feeds
import com.leakyquill.bb84.R

class CommunityFeedsAdapter(private var mContext : Context,
                            private var feeds : List<Feeds> = emptyList())
    : RecyclerView.Adapter<CommunityFeedsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(mContext)

        return ViewHolder(inflater.inflate(R.layout.community_feeds_item, parent, false))
    }

    override fun getItemCount(): Int {
        return feeds.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        var feed = feeds[position]

        if(feed.videoUrl.isEmpty() && feed.postText.isEmpty()){

            holder.imageUrl.visibility = View.VISIBLE
            holder.textPostContainer.visibility = View.GONE

            Glide.with(holder.imageUrl)
                .load(feed.imageUrl)
                .transform(CenterCrop(), RoundedCorners(20))
                .into(holder.imageUrl)
        }
        else if(feed.imageUrl.isEmpty() && feed.videoUrl.isEmpty()){

            holder.imageUrl.visibility = View.GONE
            holder.textPostContainer.visibility = View.VISIBLE

            holder.textPost.text = feed.postText

        }
        else{

        }

        holder.comment.setOnClickListener {
            val intent : Intent = Intent(mContext, FeedsCommentActivity::class.java)
            mContext.startActivity(intent)
        }

        holder.commentContainer.setOnClickListener {
            val intent : Intent = Intent(mContext, FeedsCommentActivity::class.java)
            mContext.startActivity(intent)
        }

    }

    class ViewHolder(view : View) : RecyclerView.ViewHolder(view){
        val imageUrl : ImageView = view.findViewById(R.id.post_image)
        val textPost : ReadMoreTextView = view.findViewById(R.id.read_more_text)
        val textPostContainer : RelativeLayout = view.findViewById(R.id.text_post_layout)
        val comment : ImageView = view.findViewById(R.id.comment_image)
        val like : ImageView = view.findViewById(R.id.like_image)
        val commentContainer : RelativeLayout = view.findViewById(R.id.comment_container)
        val likeContainer : RelativeLayout = view.findViewById(R.id.like_container)
    }
}