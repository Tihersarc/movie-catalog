package com.example.movie_catalog

import com.google.gson.Gson
import kotlinx.serialization.SerialName
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://api.themoviedb.org/3/"
const val API_KEY = "c5b4f8513163558f732835a0476916d6"

private val gsonInstance = Gson()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface MovieApiService {
    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int
    ): Response<MovieResponse>
}

data class MovieResponse(
    val results: List<Movie>,
    val page: Int,
    @SerialName("total_pages") val totalPages: Int
)

object MovieApi {
    val retrofitService: MovieApiService by lazy {
        retrofit.create(MovieApiService::class.java)
    }
}
