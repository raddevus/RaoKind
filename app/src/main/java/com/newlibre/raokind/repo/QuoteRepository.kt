package  com.newlibre.raokind.repo

import android.util.Log
import io.ktor.client.*
import io.ktor.client.call.body
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter

public class QuoteRepository {
    private val client = HttpClient(CIO) {

    }

    suspend fun fetchDailyQuote(): String? {
        return try {
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd") // Custom ISO format

            val targetDate = LocalDate.now().format(formatter)
            val prodUrl = "https://newlibre.com/kind/api/"
            val devUrl = "http://192.168.5.195:7103/"
            val baseUrl = devUrl
            val targetUrl = "${baseUrl}Quote/GetDailyQuote?iso8601Date="
            Log.d("TEST", "targetDate : $targetDate")
            val qr : String =  client.get("${targetUrl}${targetDate}").body() // Deserialize JSON response
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

data class QuoteResponse(val quote: Quote)

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
