package com.example.flixster
import com.google.gson.annotations.SerializedName

/**
 * The Model for storing a single movie from The Movie DB
 *
 * SerializedName tags MUST match the JSON response for the
 * object to correctly parse with the gson library.
 */

class Movie(poster_path: String, title: String, overview: String) {

    @JvmField
    @SerializedName("poster_path")
    var poster_path: String? = poster_path

    @JvmField
    @SerializedName("title")
    var title: String? = title

    @SerializedName("overview")
    var overview: String? = overview
}