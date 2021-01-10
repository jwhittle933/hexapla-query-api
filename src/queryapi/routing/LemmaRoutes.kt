package org.hexapla.queryapi.routing

import org.hexapla.queryapi.data.lemma.*
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import org.hexapla.queryapi.data.QueryAPIData
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

fun Route.lemmaRoutes(dataStore: QueryAPIData) {
    route("/lemmas") {
        get {
            val lemmas =  transaction {
                Lemmas.selectAll().map {
                    SerialLemma(
                        it[Lemmas.lemma],
                        it[Lemmas.lemma_start],
                        it[Lemmas.lemma_stop],
                        it[Lemmas.comments],
                        it[Lemmas.classic],
                        it[Lemmas.hebrew]
                    )
                }
            }
            call.respond(lemmas)
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