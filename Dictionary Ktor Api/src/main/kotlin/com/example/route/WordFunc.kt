package com.example.route

import com.example.model.Dictionary
import com.example.model.dictionary
import kotlinx.serialization.json.Json
import java.io.InputStreamReader

object WordFunc {
    suspend fun getWords() {
        try {
            val jsonString = javaClass.classLoader?.getResourceAsStream("dictionary.json").use { stream ->
                stream?.let {
                    InputStreamReader(it).use { reader ->
                        reader.readText()
                    }
                }
            }
            if (jsonString != null) {
                val a = Json.decodeFromString<Dictionary>(jsonString)
                dictionary.addAll(a.words)
            }
        } catch (_: Exception) {
        }
    }
}
