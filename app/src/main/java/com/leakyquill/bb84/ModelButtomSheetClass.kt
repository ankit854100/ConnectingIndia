package com.leakyquill.bb84

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ModelButtomSheetClass : BottomSheetDialogFragment() {

    private lateinit var mListener: BottomSheetListener
    private lateinit var status : ImageView
    private lateinit var questions : ImageView
    private lateinit var poll : ImageView
    private lateinit var camera : ImageView
    private lateinit var files : ImageView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var view : View = inflater.inflate(R.layout.bottom_model_sheet_item, container, false)

        status = view.findViewById(R.id.status)
        questions = view.findViewById(R.id.questions)
        poll = view.findViewById(R.id.poll)
        camera = view.findViewById(R.id.camera)
        files = view.findViewById(R.id.files)

        status.setOnClickListener {
            mListener.onButtonClicked("status")
            dismiss()
        }

        questions.setOnClickListener {
            mListener.onButtonClicked("questions")
            dismiss()
        }

        poll.setOnClickListener {
            mListener.onButtonClicked("poll")
            dismiss()
        }

        camera.setOnClickListener {
            mListener.onButtonClicked("camera")
            dismiss()
        }

        files.setOnClickListener {
            mListener.onButtonClicked("files")
            dismiss()
        }

        return view
    }

    interface BottomSheetListener{
        fun onButtonClicked(s : String)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        mListener = try {
            context as BottomSheetListener
        } catch (e: ClassCastException) {
            throw ClassCastException(
                "$context must implement BottomSheetListener"
            )
        }
    }
}