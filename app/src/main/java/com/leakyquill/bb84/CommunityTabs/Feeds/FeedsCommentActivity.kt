package com.leakyquill.bb84.CommunityTabs.Feeds

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.leakyquill.bb84.Adapter.CommentAdapter
import com.leakyquill.bb84.Model.Comments
import com.leakyquill.bb84.R
import kotlinx.android.synthetic.main.activity_feeds_comment.*

class FeedsCommentActivity : AppCompatActivity() {

    private lateinit var commentRecyclerView: RecyclerView
    private lateinit var commentAdapter : CommentAdapter

    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feeds_comment)

        commentRecyclerView = findViewById(R.id.feeds_comment_recycler_view)

        commentAdapter = CommentAdapter(getComments())
        commentRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        commentRecyclerView.adapter = commentAdapter

        back_button.setOnClickListener {
            this@FeedsCommentActivity.finish()
        }
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

    override fun onBackPressed() {

        this@FeedsCommentActivity.finish()
        super.onBackPressed()
    }
}