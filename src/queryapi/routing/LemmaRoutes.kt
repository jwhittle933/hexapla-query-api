package org.hexapla.queryapi.routing

import org.hexapla.queryapi.data.lemma.*
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.lemmaRoutes() {
    route("/lemmas")  {
        get {
            if (lemmas.isNotEmpty()) call.respond(lemmas)
            else call.respondText("No lemmas", status = HttpStatusCode.NotFound)
        }
        get("{id}") {
            val id = call.parameters["id"] ?: return@get call.respondText(
                "Missing id",
                status = HttpStatusCode.BadRequest
            )

            val lemma = lemmas.find { it.id.toString() == id } ?: call.respondText(
                "Invalid ID",
                status = HttpStatusCode.NotFound
            )

            call.respond(lemma)
        }
    }
}