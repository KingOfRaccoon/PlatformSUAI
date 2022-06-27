package ru.castprograms.platformsuai

class Greeting {
    fun greeting(): String {
        return "Hello, ${Platform().platform}!"
    }
}