package org.hexapla

import io.ktor.application.*
import io.ktor.response.*
import io.ktor.request.*
import io.ktor.routing.*
import io.ktor.http.*
import io.ktor.sessions.*
import io.ktor.features.*
import io.ktor.websocket.*
import io.ktor.http.cio.websocket.*
import java.time.*
import io.ktor.auth.*
import io.ktor.gson.*
import io.ktor.client.*
import org.hexapla.queryapi.QueryAPI
import org.hexapla.queryapi.data.DataStore
import org.hexapla.queryapi.data.lemma.Lemma
import org.hexapla.queryapi.data.lemma.Lemmas
import org.hexapla.queryapi.env.Environment
import org.hexapla.queryapi.routing.Router
import org.hexapla.queryapi.routing.attestationRoutes
import org.hexapla.queryapi.routing.lemmaRoutes
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {
    install(Sessions) {
        cookie<MySession>("MY_SESSION") {
            cookie.extensions["SameSite"] = "lax"
        }
    }

    install(CORS) {
        method(HttpMethod.Options)
        method(HttpMethod.Put)
        method(HttpMethod.Delete)
        method(HttpMethod.Patch)
        header(HttpHeaders.Authorization)
        header("MyCustomHeader")
        allowCredentials = true
        anyHost() // @TODO: Don't do this in production if possible. Try to limit it.
    }

    install(io.ktor.websocket.WebSockets) {
        pingPeriod = Duration.ofSeconds(15)
        timeout = Duration.ofSeconds(15)
        maxFrameSize = Long.MAX_VALUE
        masking = false
    }

    install(Authentication) {
    }

    install(ContentNegotiation) {
        gson {
            setPrettyPrinting()
        }
    }

    //val client = HttpClient() {
    //}

    val dataStore = DataStore()
    val router = Router()
    val env = Environment()
    val queryAPI = QueryAPI(env, dataStore, router)

    routing {
        lemmaRoutes(dataStore)
        attestationRoutes()

        get("/") {
            call.respondText("HELLO WORLD!", contentType = ContentType.Text.Plain)
        }

        get("/session/increment") {
            val session = call.sessions.get<MySession>() ?: MySession()
            call.sessions.set(session.copy(count = session.count + 1))
            call.respondText("Counter is ${session.count}. Refresh to increment.")
        }

        webSocket("/sock/echo") {
            send(Frame.Text("Hi from server"))
            while (true) {
                val frame = incoming.receive()
                if (frame is Frame.Text) {
                    send(Frame.Text("Client said: " + frame.readText()))
                }
            }
        }

        get("/json/gson") {
            call.respond(mapOf("hello" to "world"))
        }
    }
}

data class MySession(val count: Int = 0)

