package com.example.finalproyec

import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL

object CrunchifyGetPingStatus {

    @Throws(Exception::class)
    @JvmStatic

/*
    fun main(args: Array<String>) {
        val hostList = arrayOf("https://mercadolibre.com","http://crunchify.com", "http://yahoo.com", "http://www.ebay.com",
            "https://google.com",
            "http://www.example.co", "https://paypal.com",
            "http://bing.com/", "http://techcrunch.com/", "http://mashable.com/",
            "https://thenextweb.com/", "http://wordpress.com/",
            "http://wordpress.org/", "http://example.com/", "http://sjsu.edu/",
            "https://ebay.co.uk/", "http://google.co.uk/", "http://wikipedia.org/")
        for (i in hostList.indices) {
            val url = hostList[i]
            getStatus(url)
        }
        println("Task completed...")
    }
*/

//    @Throws(IOException::class)
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