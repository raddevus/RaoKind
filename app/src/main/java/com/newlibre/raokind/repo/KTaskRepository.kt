package com.newlibre.raokind.repo

import android.content.Context
import android.util.Log
import com.google.gson.reflect.TypeToken
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.request.get
import java.io.File
import com.google.gson.Gson
import com.newlibre.raokind.AppConstants

class KTaskRepository(context: Context) {
    val context = context
    var gson = Gson()
    private val client = HttpClient(CIO) {

    }

    val allKTasks : List<KTask> = emptyList()
    var kTasksJson: String? = null

    suspend fun getAllTasks(): String? {
        return try {
            if (loadKTasksFromLocalFile()){
                return kTasksJson
            }

            val targetUrl = "${AppConstants.API_BASE_URL}KTask/GetAll"
            var ktaskResponseJson : String =  client.get("${targetUrl}").body()
            Log.d("TEST", "${ktaskResponseJson}")
            var ktr :KTaskResponse = gson.fromJson<KTaskResponse>(ktaskResponseJson,
                object : TypeToken<KTaskResponse>(){}.type
            )
            Log.d("TEST", "ktr: ${ktr.tasks.toString()}");


            return ktaskResponseJson
        } catch (e: Exception) {
            Log.d("TEST","Error fetching ktask: ${e.message}")
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

data class KTaskResponse(val success: Boolean, val tasks: List<KTask>)

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