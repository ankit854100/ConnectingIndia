package com.anand.connectingindia

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import com.anand.connectingindia.Fragments.StoriesFragment
import jp.shts.android.storiesprogressview.StoriesProgressView
import kotlinx.android.synthetic.main.activity_stories.*

class StoriesActivity : AppCompatActivity() {


    private val PROGRESS_COUNT = 3
    private var counter = 0


    private val imageArray : IntArray = intArrayOf(
        R.drawable.sample1,
        R.drawable.sample2,
        R.drawable.sample3
    )

    private var pressTime = 0L
    private val limit = 500L

    private val onTouchListener: View.OnTouchListener = object : View.OnTouchListener {
        override fun onTouch(v: View?, event: MotionEvent): Boolean {
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    pressTime = System.currentTimeMillis()
                    stories.pause()
                    return false
                }
                MotionEvent.ACTION_UP -> {
                    val now = System.currentTimeMillis()
                    stories.resume()
                    return limit < now - pressTime
                }
            }
            return false
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_stories)

        stories.setStoriesCount(PROGRESS_COUNT)
        stories.setStoryDuration(3000L)

        image.setImageResource(imageArray[counter])
        stories.startStories()

        skip.setOnClickListener {
            stories.skip()
        }
        skip.setOnTouchListener(onTouchListener)

        reverse.setOnClickListener {
            stories.reverse()
        }
        reverse.setOnTouchListener(onTouchListener)

        stories.setStoriesListener(object : StoriesProgressView.StoriesListener{
            override fun onComplete() {

                Toast.makeText(this@StoriesActivity, " Stories are completed", Toast.LENGTH_LONG).show()
                this@StoriesActivity.finish();

            }

            override fun onPrev() {
                if ((counter -1) < 0){
                    return
                }
                image.setImageResource(imageArray[--counter])
            }

            override fun onNext() {
                if ((counter) > 3)
                {
                    return
                }
                image.setImageResource(imageArray[++counter])
            }
        })

    }

    override fun onDestroy() {
        stories.destroy()
        super.onDestroy()
    }
}
