package org.hexapla.queryapi.data.translators

import org.hexapla.queryapi.data.attestations.Attestation
import org.hexapla.queryapi.data.attestations.Attestations
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable

object Translators : IntIdTable() {
    val name = varchar("name", 255)
    val abbreviation = varchar("abbreviation", 255)
}

class Translator(id : EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<Translator>(Translators)
    var name by Translators.name
    var abbreviation by Translators.abbreviation
    val attestation by Attestation optionalReferrersOn Attestations.translator
}

