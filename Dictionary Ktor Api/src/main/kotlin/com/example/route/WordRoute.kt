package com.example.route

import com.example.model.dictionary
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

fun Route.wordRouting() {
    route("/word") {
        get {
            val english = call.parameters["english"] ?: return@get call.respondText(
                "Missing word",
                status = HttpStatusCode.BadRequest
            )
            val param = call.request.queryParameters["english"]
            if (param != null) {
                dictionary.forEach {
                    if (it.english == param) {
                        call.respond(it)
                        return@get
                    }
                }
                call.respondText(
                    "No word for $english",
                    status = HttpStatusCode.NotFound
                )
            }
        }
    }
}
