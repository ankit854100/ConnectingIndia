package com.leakyquill.bb84.Callback

import androidx.recyclerview.widget.DiffUtil
import com.leakyquill.bb84.Model.Photos
import com.leakyquill.bb84.Model.Spot

class SpotDiffCallback(
    private val old: List<Photos>,
    private val new: List<Photos>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return old.size
    }

    override fun getNewListSize(): Int {
        return new.size
    }

    override fun areItemsTheSame(oldPosition: Int, newPosition: Int): Boolean {
        return old[oldPosition].id == new[newPosition].id
    }

    override fun areContentsTheSame(oldPosition: Int, newPosition: Int): Boolean {
        return old[oldPosition] == new[newPosition]
    }

}