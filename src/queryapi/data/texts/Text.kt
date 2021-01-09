package org.hexapla.queryapi.data.texts

import org.hexapla.queryapi.data.books.*
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable

object LXXs : IntIdTable() {
    val book_id = integer("book_id")
    val book = reference("book", Books)
    val chapter = integer("chapter")
    val verse = integer("verse")
    val text = text("text")
}

class LXX(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<LXX>(LXXs)
    var chapter by LXXs.chapter
    var verse by LXXs.verse
    var text by LXXs.text

    var book by Book referencedOn LXXs.book
}