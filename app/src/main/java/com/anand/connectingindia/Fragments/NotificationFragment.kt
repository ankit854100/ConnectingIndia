package com.anand.connectingindia.Fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

import com.anand.connectingindia.R
import com.anand.connectingindia.SignUpActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_notification.*

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

