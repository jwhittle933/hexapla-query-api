package org.hexapla.queryapi.data.books

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable

object Books : IntIdTable() {
    val name = varchar("name", 255)
    val sort_order = integer("sort_order")
    val status = varchar("status", 255)
}

class Book(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<Book>(Books)

    val name by Books.name
    val sort_order by Books.sort_order
    val status by Books.status
}