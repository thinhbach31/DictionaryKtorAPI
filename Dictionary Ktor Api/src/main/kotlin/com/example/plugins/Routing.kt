package com.example.plugins

import com.example.route.WordFunc
import com.example.route.wordRouting
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

fun Application.configureRouting() {
    routing {
        CoroutineScope(Job()).launch {
            WordFunc.getWords()
        }
        wordRouting()
    }
}
