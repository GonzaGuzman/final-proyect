package com.example.finalproyect

import java.net.HttpURLConnection
import java.net.URL

class PlatformRepository {
    fun getStatus(url: String): Boolean {
        return try {
            val siteURL = URL(url)
            val connection = siteURL.openConnection() as HttpURLConnection
            connection.requestMethod = "GET"
            connection.connectTimeout = 3000
            connection.connect()
            val code: Int = connection.responseCode
            code in 200..399
        } catch (e: Exception) {
            false
        }
    }
}