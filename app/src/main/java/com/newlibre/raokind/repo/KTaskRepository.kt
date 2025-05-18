package com.newlibre.raokind.repo

import android.content.Context
import android.util.Log
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.request.get
import java.io.File

class KTaskRepository(context: Context) {
    val context = context
    private val client = HttpClient(CIO) {

    }

    val allKTasks : List<KTask> = emptyList()
    var kTasksJson: String? = null

    suspend fun getAllTasks(): String? {
        return try {

            val prodUrl = "https://newlibre.com/kind/api/"
            // val devUrl = "http://192.168.5.195:7103/"
            val devUrl = "http://192.168.5.126:7103/"
            val baseUrl = devUrl
            val targetUrl = "${baseUrl}KTask/GetAll"
            val ktaskResponseJson : String =  client.get("${targetUrl}").body()
            Log.d("TEST", "after calling get")
            Log.d("TEST", ktaskResponseJson)
            return ktaskResponseJson
        } catch (e: Exception) {
            Log.d("TEST","Error fetching quote: ${e.message}")
            null
        }
    }

    private fun loadKTasksFromLocalFile() : Boolean{
        val appContext = context.applicationContext
        val file = File(appContext.filesDir, "ktasks.json")
        if (file.exists()) {
            kTasksJson = file.readText()
            return true
        }
        return false
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