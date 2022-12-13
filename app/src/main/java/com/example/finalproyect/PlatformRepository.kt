package com.example.finalproyect

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL
import java.net.UnknownHostException

class PlatformRepository {
    suspend fun getStatus(url: String): Boolean = withContext(Dispatchers.IO){
        return@withContext try {
            val siteURL = URL(url)
            val connection = siteURL.openConnection() as HttpURLConnection
            connection.requestMethod = "GET"
            connection.connectTimeout = 3000
            connection.connect()
            val code: Int = connection.responseCode
            code in 200..399
        }  catch (e: IOException) {
            Log.d("Error", e.message.toString())
            false
        } catch (e: UnknownHostException) {
            Log.d("Error", e.message.toString())
            false
        } catch (e: MalformedURLException) {
            Log.d("Error", e.message.toString())
            false
        }
    }


    suspend fun canConnect(url: String): Boolean = withContext(Dispatchers.IO) {

        return@withContext try {
            // We want to check the current URL
            HttpURLConnection.setFollowRedirects(false)
            val httpURLConnection = (URL(url).openConnection() as HttpURLConnection)

            // We don't need to get data
            httpURLConnection.requestMethod = "HEAD"

            // Some websites don't like programmatic access so pretend to be a browser
            httpURLConnection.setRequestProperty(
                "User-Agent",
                "Mozilla/5.0 (Windows; U; Windows NT 6.0; en-US; rv:1.9.1.2)" +
                        " Gecko/20090729 Firefox/3.5.2 (.NET CLR 3.5.30729)"
            )
            // We only accept response code 200
            httpURLConnection.responseCode == HttpURLConnection.HTTP_OK
        } catch (e: IOException) {
            Log.d("Error", e.message.toString())
            false
        } catch (e: UnknownHostException) {
            Log.d("Error", e.message.toString())
            false
        } catch (e: MalformedURLException) {
            Log.d("Error", e.message.toString())
            false
        }
    }
}