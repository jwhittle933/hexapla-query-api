package org.hexapla.queryapi

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import org.hexapla.queryapi.data.DataStore
import org.hexapla.queryapi.env.Environment
import org.hexapla.queryapi.routing.Router

class QueryAPI(env: Environment, dataStore: DataStore, router: Router) {
}