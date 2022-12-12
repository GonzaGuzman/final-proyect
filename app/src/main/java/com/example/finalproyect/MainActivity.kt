package com.example.finalproyect

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.ViewModelProvider
import com.example.finalproyect.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    companion object {
        val TAG = "mainActivity"
    }

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: ViewModelPlatform

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[ViewModelPlatform::class.java]

        initComponent()

       /* Thread {
            runOnUiThread {
            }
        }.start()*/
    }

    fun initComponent() {
        binding.etName.doAfterTextChanged {
            viewModel.setName(it.toString())
        }

        binding.etWebSite.doAfterTextChanged {
            viewModel.setDir(it.toString())
        }
        setBtnConfirmVisibilityListener()
    }

    private fun setBtnConfirmVisibilityListener() {
        viewModel.statusSite.observe(this) {
            viewModel.validateSite()
            binding.btnConfirm.isEnabled = it
        }
    }

    /*fun getStatus(url: String): String {
        var result = ""
        var code = 200
        try {
            val siteURL = URL(url)
            val connection = siteURL.openConnection() as HttpURLConnection
            connection.requestMethod = "GET"
            connection.connectTimeout = 3000
            connection.connect()
            code = connection.responseCode
            result = if (code in 200..399) {
                "-> Green <-\tCode: $code"
            } else {
                "-> Yellow <-\tCode: $code"
            }
        } catch (e: Exception) {
            result = "-> Red <-\t" + "Wrong domain - Exception: " + e.message
        }
        println("$url\t\tStatus:$result")
        return result
    }*/


/*
    private fun exists(URLName: String?): Boolean {
        return try {
            HttpURLConnection.setFollowRedirects(false)
            // note : you may also need
            // HttpURLConnection.setInstanceFollowRedirects(false)
            val con = URL(URLName)
                .openConnection() as HttpURLConnection
            con.requestMethod = "HEAD"
            con.responseCode == HttpURLConnection.HTTP_OK
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    fun isHostAvailable(urlPath: String): Boolean {
        try {
            val url = URL(urlPath)
            val connection: HttpURLConnection = url.openConnection() as HttpURLConnection
            connection.setRequestProperty("Connection", "close")
            connection.connectTimeout = 3000
            connection.connect()

            return when (connection.responseCode) {
                200, 403 -> true
                else -> false
            }

        } catch (e: Exception) {
            when (e) {
                is MalformedURLException -> "loadLink: Invalid URL ${e.message}"
                is IOException -> "loadLink: IO Exception reading data: ${e.message}"
                is SecurityException -> {
                    e.printStackTrace()
                    "loadLink: Security Exception. Needs permission? ${e.message}"
                }
                else -> "Unknown error: ${e.message}"
            }
        }
        return false
    }
*/

}


