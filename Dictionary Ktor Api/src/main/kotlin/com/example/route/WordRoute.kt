package com.example.route

import com.example.model.Word
import com.example.model.dictionary
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.text.get

fun Route.wordRouting() {
    route("/word") {

        CoroutineScope(Job()).launch {
            WordFunc.getWords()
        }

        get {
            if (dictionary.isNotEmpty()) {
                call.respond(dictionary)
            } else {
                call.respondText("No word found", status = HttpStatusCode.OK)
            }
        }
        get("{english?}") {
            val english = call.parameters["english"] ?: return@get call.respondText(
                "Missing word",
                status = HttpStatusCode.BadRequest
            )
            val word =
                dictionary.find { it.english == english } ?: return@get call.respondText(
                    "No word for $english",
                    status = HttpStatusCode.NotFound
                )
            call.respond(word.german)
        }
        post {
            val word = call.receive<Word>()
            dictionary.add(word)
            call.respondText("Word stored correctly", status = HttpStatusCode.Created)
        }
        delete {

        }
    }
}
