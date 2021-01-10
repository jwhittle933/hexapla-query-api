package org.hexapla.queryapi.data.lemma

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable

@Serializable
data class SerialLemma(
    val lemma: String?,
    val lemma_start: Int?,
    val lemma_stop: Int?,
    val comments: String?,
    val classic: Boolean?,
    val hebrew: String?
    )

object Lemmas : IntIdTable() {
    val lemma  = text("lemma")
    val lemma_start = integer("lemma_start")
    val lemma_stop = integer("lemma_stop")
    val comments = text("comments")
    val classic = bool("classic")
    val hebrew = text("hebrew")
}

class Lemma(id: EntityID<Int>) : IntEntity(id) {
        companion object : IntEntityClass<Lemma>(Lemmas)
        var lemma  by Lemmas.lemma
        var lemma_start by Lemmas.lemma_start
        var lemma_stop by Lemmas.lemma_stop
        var comments by Lemmas.comments
        var classic by Lemmas.classic
    var hebrew by Lemmas.hebrew
}


val lemmas = mutableListOf<Lemma>()