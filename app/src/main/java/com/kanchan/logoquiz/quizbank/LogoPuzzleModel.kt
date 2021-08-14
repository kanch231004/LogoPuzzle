package com.kanchan.logoquiz.quizbank

import com.google.gson.annotations.SerializedName

data class LogoPuzzleModel(
    @SerializedName("imgUrl") val imgUrl: String,
    @SerializedName("name") val name: String,
    var isAsked: Boolean = false /** This is added so that same question isn't
    shown twice as we are randomly selecting question */
)

