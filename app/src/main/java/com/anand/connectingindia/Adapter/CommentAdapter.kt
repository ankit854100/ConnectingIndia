package com.anand.connectingindia.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.anand.connectingindia.Model.Comments
import com.anand.connectingindia.R

class CommentAdapter(
    private var comment: List<Comments> = emptyList()
) : RecyclerView.Adapter<CommentAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(inflater.inflate(R.layout.comment_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val commentItem = comment[position]

        holder.commentText.text = commentItem.userComment
    }

    override fun getItemCount(): Int {
        return comment.size
    }


    class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {

        var commentText : TextView = view.findViewById(R.id.comment)

    }
}