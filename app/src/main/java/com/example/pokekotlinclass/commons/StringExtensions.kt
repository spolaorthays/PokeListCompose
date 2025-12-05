package com.example.pokekotlinclass.commons

fun String.toUpperFirstChar(): String {
    val value = this[0].uppercaseChar()
    return this.replace(this, value + this.substring(1, this.length))
}