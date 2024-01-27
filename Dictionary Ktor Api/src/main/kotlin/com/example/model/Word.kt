package com.example.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Word(val id: Int, val english: String, val german: String)

@Serializable
data class Dictionary(
    @SerialName("words")
    val words: MutableList<Word>
)

val dictionary = mutableListOf<Word>()
