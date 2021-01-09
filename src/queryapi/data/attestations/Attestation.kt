package org.hexapla.queryapi.data.attestations

import org.hexapla.queryapi.data.lemma.*
import org.hexapla.queryapi.data.translators.*
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable

object Attestations : IntIdTable() {
    val lemma_id = integer("lemma_id")
    val attestation  = text("attestation")
    val evidence = varchar("evidence", 255)
    val certainty  = integer("certainty")
    val comments  = text("comments")
    val translator_id = integer("translator_id")
    val translator_relationship_id = integer("translator_relationship_id")
    val classic = bool("classic")

    val lemma = reference("lemma", Lemmas)
    val translator = reference("translator", Translators).nullable()
    val translatorRelationship = reference("translator_relationship", TranslatorRelationships).nullable()
}

class Attestation(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<Attestation>(Attestations)
    var attestation by Attestations.attestation
    var evidence by Attestations.evidence
    var certainty by Attestations.certainty
    var comments by Attestations.comments
    var translator_id by Attestations.translator_id
    var translator_relationship_id by Attestations.translator_relationship_id
    var classic by Attestations.classic

    var lemma by Lemma referencedOn Attestations.lemma
    var translator by Translator optionalReferencedOn Attestations.translator
    var translatorRelationship by TranslatorRelationship optionalReferencedOn Attestations.translatorRelationship
}

val attestations = mutableListOf<Attestation>()