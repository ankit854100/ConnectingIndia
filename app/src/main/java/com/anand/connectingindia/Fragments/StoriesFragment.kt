package com.anand.connectingindia.Fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast

import com.anand.connectingindia.R
import com.anand.connectingindia.StoriesActivity

/**
 * A simple [Fragment] subclass.
 */
class StoriesFragment : Fragment() {

    private lateinit var playImage : ImageView
    private lateinit var playImage1 : ImageView
    private lateinit var intent: Intent

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view : View = inflater!!.inflate(R.layout.fragment_stories, container, false)

        playImage = view.findViewById(R.id.play)
        playImage1 = view.findViewById(R.id.play01)

        playImage.setOnClickListener {
            Toast.makeText(context, "play button is clicked", Toast.LENGTH_SHORT).show()
            intent = Intent(context, StoriesActivity::class.java)
            startActivity(intent)
        }

        playImage1.setOnClickListener {
            Toast.makeText(context, "play button is clicked", Toast.LENGTH_SHORT).show()
            intent = Intent(context, StoriesActivity::class.java)
            startActivity(intent)
        }

        return view
    }

}
