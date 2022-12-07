package com.example.finalproyect

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.finalproyec.CrunchifyGetPingStatus
import java.io.IOException
import java.net.*

class MainActivity : AppCompatActivity() {
    companion object {
        val TAG = "mainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        CrunchifyGetPingStatus.getStatus("https://mercadolibre.com")
    }

    fun pingHost(host: String?, port: Int, timeout: Int): Boolean {
        try {
            Socket().use { socket ->
                socket.connect(InetSocketAddress(host, port), timeout)
                return true
            }
        } catch (e: IOException) {
            return false // Either timeout or unreachable or failed DNS lookup.
        }
    }

    fun ping(params: String) {
        try {
            val url = URL("http://" + params[0])
            val urlc = url.openConnection() as HttpURLConnection
            //      urlc.setRequestProperty("User-Agent", "Android Application:" + Z.APP_VERSION)
            urlc.setRequestProperty("Connection", "close")
            urlc.connectTimeout = 1000 * 30 // Timeout is in seconds
            urlc.connect()
            if (urlc.responseCode == 200) {
                Log.d(TAG, "getResponseCode == 200")

            }
        } catch (e1: MalformedURLException) {
            e1.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }


    fun getStatus(url: String): String {
        var result = ""
        var code = 200
        try {
            val siteURL = URL(url)
            val connection = siteURL.openConnection() as HttpURLConnection
            connection.requestMethod = "GET"
            connection.connectTimeout = 3000
            connection.connect()
            code = connection.responseCode
            result = if (code == 200) {
                "-> Green <-\tCode: $code"
            } else {
                "-> Yellow <-\tCode: $code"
            }
        } catch (e: Exception) {
            result = "-> Red <-\t" + "Wrong domain - Exception: " + e.message
        }
        println("$url\t\tStatus:$result")
        return result
    }
}

