package com.example.finalproyec

import android.util.Log
import java.io.IOException
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL


object CrunchifyGetPingStatus {

    @Throws(Exception::class)
    @JvmStatic

    fun main(args: Array<String>) {
        val hostList = arrayOf("https://mercadolibre.com",
            "https://mercadolibre.com.ar",

            "http://crunchify.com",
            "http://yahoo.com",
            "http://www.ebay.com",
            "https://google.com",
            "http://www.example.co",
            "https://paypal.com",
            "http://bing.com/",
            "http://techcrunch.com/",
            "http://mashable.com/",
            "https://thenextweb.com/",
            "http://wordpress.com/",
            "http://wordpress.org/",
            "http://example.com/",
            "http://sjsu.edu/",
            "https://ebay.co.uk/",
            "http://google.co.uk/",
            "http://wikipedia.org/")
        for (i in hostList.indices) {
            val url = hostList[i]
            getStatus(url)
            println(exists(url))
          println(isConnectedToThisServer(url))
        }
        println("Task completed...")
    }


    @Throws(IOException::class)
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

    fun exists(URLName: String?): Boolean {
        return try {
            HttpURLConnection.setFollowRedirects(true)
            // note : you may also need
            //HttpURLConnection.setInstanceFollowRedirects(false)
            val con = URL(URLName)
                .openConnection() as HttpURLConnection
            con.requestMethod = "HEAD"
            con.responseCode == HttpURLConnection.HTTP_OK
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }
    fun isConnectedToThisServer(host: String):Boolean {
        val runtime = Runtime.getRuntime()
        try {
            val ipProcess = runtime.exec("/system/bin/ping -c 1 8.8.8.8$host")
            val exitValue = ipProcess.waitFor()
            return (exitValue == 0);
        } catch (e: IOException) {
            e.printStackTrace();
        } catch (e: InterruptedException ) {
            e.printStackTrace();
        }
        return false;
    }

}