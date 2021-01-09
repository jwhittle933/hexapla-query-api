package org.hexapla.queryapi.env

class Environment {
    private val env : MutableMap<String, String> = System.getenv()
    fun find(value: String): String? = env[value]
}