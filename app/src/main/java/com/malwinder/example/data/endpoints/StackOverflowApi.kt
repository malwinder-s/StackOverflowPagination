package com.malwinder.example.data.endpoints

import com.malwinder.example.domain.models.SearchResult
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

private const val PREFIX = "2.2/search?order=desc&sort=activity&site=stackoverflow"

interface StackOverflowApi {

    @GET(PREFIX)
    fun getSearchResults(
            @Query("page") page: Int,
            @Suppress("SpellCheckingInspection") @Query("pagesize") perPage: Int,
            @Suppress("SpellCheckingInspection") @Query("intitle") intitle: String
            ): Single<Response<SearchResult>>
}