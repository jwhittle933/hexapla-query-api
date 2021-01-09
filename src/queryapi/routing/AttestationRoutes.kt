package org.hexapla.queryapi.routing

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*

class AttestationRoutes {
}

fun Route.attestationRoutes() {
    route("/attestations") {
        get {
            //
        }
        get("{id}") {

        }
    }
}

