package com.newlibre.raokind.repo

import android.util.Log
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.request.get
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter

class KTaskRepository {
    private val client = HttpClient(CIO) {

    }

    suspend fun getAllTasks(): String? {
        return try {

            val prodUrl = "https://newlibre.com/kind/api/"
            val devUrl = "http://192.168.5.195:7103/"
            val baseUrl = devUrl
            val targetUrl = "${baseUrl}KTask/GetAll"
            val allKTasks : String =  client.get("${targetUrl}").body()
            Log.d("TEST", "after calling get")
            Log.d("TEST", allKTasks)
            return allKTasks
        } catch (e: Exception) {
            Log.d("TEST","Error fetching quote: ${e.message}")
            null
        }
    }

    fun close() {
        client.close() // Properly close the HTTP client
    }
}

data class KTaskResponse(val success: Boolean, val quote: KTask)

data class KTask(
    val id: Int,
    val userId: Int,
    val category: String,
    val subCategory: String,
    val description: String,
    val status: String,
    val created: String,
    val updated: String?,
    val active: Boolean
)