package com.leakyquill.bb84.Model

data class Comments(
    val commentId : Long = counter++,
    val userComment : String

) {
    companion object {
        private var counter = 0L
    }
}