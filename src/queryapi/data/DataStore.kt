package org.hexapla.queryapi.data

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.jetbrains.exposed.sql.*
import java.util.*

interface QueryAPIData {
    //
}

class DataStore: QueryAPIData {
    init {
        val props = Properties()
        props.setProperty("dataSourceClassName", "org.postgresql.ds.PGSimpleDataSource");
        props.setProperty("dataSource.user", "jonathanwhittle");
        props.setProperty("dataSource.password", "");
        props.setProperty("dataSource.databaseName", "hexapla_api_dev");
        // props.setProperty("dataSource.portNumber", "5432");
        // props.setProperty("dataSource.serverName", "locahost");
        val config =  HikariConfig(props)

        config.schema = "hexapla"
        val ds = HikariDataSource(config)
        Database.connect(ds)
    }
}