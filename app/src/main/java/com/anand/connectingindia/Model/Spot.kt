package com.anand.connectingindia.Model

data class Spot(
    val id: Long = counter++,
    val name: String,
    val city: String,
    val url: String,
    val postText : String
) {
    companion object {
        private var counter = 0L
    }
}