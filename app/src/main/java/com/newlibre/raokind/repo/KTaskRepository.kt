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
    val localKTaskFile = "ktasks.json"

    var gson = Gson()
    private val client = HttpClient(CIO) {

    }

    object AllKTasks {
        var allKTasks : MutableList<KTask> = mutableListOf()
    }
    var kTasksJson: String? = null

    suspend fun getAllTasks(): MutableList<KTask> {
        return try {
            if (loadKTasksFromLocalFile()){
                try {
                    AllKTasks.allKTasks = gson.fromJson<MutableList<KTask>>(
                        kTasksJson,
                        object : TypeToken<MutableList<KTask>>() {}.type
                    )
                    return AllKTasks.allKTasks
                }
                catch (ex: Exception){
                    val appContext = context.applicationContext
                    val file = File(appContext.filesDir, localKTaskFile)
                    file.delete()
                }
            }

            val targetUrl = "${AppConstants.API_BASE_URL}KTask/GetAll"
            var ktaskResponseJson : String =  client.get("${targetUrl}").body()
            Log.d("TEST", "${ktaskResponseJson}")
            var ktr :KTaskResponse = gson.fromJson<KTaskResponse>(ktaskResponseJson,
                object : TypeToken<KTaskResponse>(){}.type
            )
            if (ktr.success) {
                Log.d("TEST", "ktr: ${ktr.tasks[0].toString()}");
                val KTaskListAsJson = gson.toJson(ktr.tasks)
                saveKTasksToLocalFile(KTaskListAsJson)
                Log.d("TEST", "Saved KTasks as JSON: ${KTaskListAsJson}")
                AllKTasks.allKTasks = ktr.tasks
                return ktr.tasks
            }
            return AllKTasks.allKTasks
        } catch (e: Exception) {
            Log.d("TEST","Error fetching ktask: ${e.message}")
            AllKTasks.allKTasks
        }
    }

    private fun loadKTasksFromLocalFile() : Boolean{
        Log.d("TEST", "Loading KTasks from file.")
        val appContext = context.applicationContext
        val file = File(appContext.filesDir, localKTaskFile)
        if (file.exists()) {
            kTasksJson = file.readText()
            return true
        }
        return false
    }

    private fun saveKTasksToLocalFile(jsonData: String) {
        Log.d("TEST", "Saving KTasks to local file.")
        val appContext = context.applicationContext
        val file = File(appContext.filesDir, localKTaskFile)
        file.writeText(jsonData)
    }

    public fun saveKTaskList(allKTasks: MutableList<KTask>){
        saveKTasksToLocalFile(gson.toJson(allKTasks))
    }

    fun close() {
        client.close() // Properly close the HTTP client
    }

}

data class KTaskResponse(val success: Boolean, val tasks: MutableList<KTask>)

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