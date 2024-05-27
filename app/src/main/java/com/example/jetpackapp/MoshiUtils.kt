package com.example.jetpackapp

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

val moshi: Moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

inline fun <reified T> fromJson(json: String) = moshi.adapter(T::class.java).fromJson(json)

inline fun <reified T> adapter(): com.squareup.moshi.JsonAdapter<T>
        = moshi.adapter(T::class.java)
