package org.hexapla.queryapi.data.translators

import org.hexapla.queryapi.data.attestations.Attestation
import org.hexapla.queryapi.data.attestations.Attestations
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable

object TranslatorRelationships: IntIdTable() {
    val description = varchar("description", 255)
    val leftSymbol = varchar("left_symbol", 5)
    val rightSymbol = varchar("right_symbol", 5)
    val aggregateSymbol = varchar("aggregate_symbol", 255)
}

class TranslatorRelationship(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<TranslatorRelationship>(TranslatorRelationships)
    var description by TranslatorRelationships.description
    var leftSymbol by TranslatorRelationships.leftSymbol
    var rightSymbol by TranslatorRelationships.rightSymbol
    var  aggregateSymbol by TranslatorRelationships.aggregateSymbol
    val attestation by Attestation optionalReferrersOn Attestations.translator
}