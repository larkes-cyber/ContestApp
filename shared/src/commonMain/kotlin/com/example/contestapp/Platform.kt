package com.example.contestapp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform