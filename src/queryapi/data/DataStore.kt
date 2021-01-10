package org.hexapla.queryapi.data

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.jetbrains.exposed.sql.*
import java.util.*
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.transaction

interface QueryAPIData {
    suspend fun <T> query(q: suspend () -> T): T
}

class DataStore: QueryAPIData {
    init {
        val props = Properties()
        props.setProperty("dataSourceClassName", "org.postgresql.ds.PGSimpleDataSource");
        props.setProperty("dataSource.user", "jonathanwhittle");
        props.setProperty("dataSource.password", "");
        props.setProperty("dataSource.databaseName", "hexapla_api_dev");
        props.setProperty("dataSource.portNumber", "5432");
        props.setProperty("dataSource.serverName", "localhost");
        val config =  HikariConfig(props)

        config.schema = "public"
        val ds = HikariDataSource(config)
        Database.connect(ds)
    }

    override suspend fun <T> query(q: suspend () -> T) : T =  newSuspendedTransaction {  q() }
}