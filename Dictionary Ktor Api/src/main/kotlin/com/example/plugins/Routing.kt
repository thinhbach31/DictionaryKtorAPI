package com.example.plugins

import com.example.route.wordRouting
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        wordRouting()
    }
}
