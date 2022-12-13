package com.example.finalproyect

fun String.addHttp(): String {
    val httpString: String = "http://www."
    return if (!this.contains(httpString)) {
        httpString + this
    } else {
        this
    }
}
