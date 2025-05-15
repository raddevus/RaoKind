package  com.newlibre.raokind.repo

import android.util.Log
import io.ktor.client.*
import io.ktor.client.call.body
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.Serializable

import kotlinx.serialization.json.Json

public class QuoteRepository {
    private val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true // Ignores unexpected fields in JSON
                isLenient = true // Allows for relaxed JSON parsing
            })
        }
    }

    suspend fun fetchDailyQuote(): String? {
        return try {
            val qr : String =  client.get("http://192.168.5.195:7103/Quote/GetDailyQuote?iso8601Date=2024-09-04").body() // Deserialize JSON response
            Log.d("TEST", "after calling get")
            return qr
        } catch (e: Exception) {
            Log.d("TEST","Error fetching quote: ${e.message}")
            null
        }
    }

    fun close() {
        client.close() // Properly close the HTTP client
    }
}

@Serializable
data class QuoteResponse(val quote: Quote)

@Serializable
data class Quote(
    val id: Int,
    val fName: String,
    val lName: String,
    val category: String?,
    val content: String,
    val dayNumber: Int,
    val created: String,
    val updated: String?,
    val active: Boolean
)
