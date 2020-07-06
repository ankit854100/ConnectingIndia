package com.leakyquill.bb84.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

import com.leakyquill.bb84.R
import com.google.firebase.auth.FirebaseAuth

class NotificationFragment : Fragment() {

    private lateinit var buttonLogout : Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view : View = inflater!!.inflate(R.layout.fragment_notification, container, false)
        // Inflate the layout for this fragment

        buttonLogout = view.findViewById(R.id.buttonLogOut)

        buttonLogout.setOnClickListener {
            FirebaseAuth.getInstance().signOut()

        }

        return view
    }

}

