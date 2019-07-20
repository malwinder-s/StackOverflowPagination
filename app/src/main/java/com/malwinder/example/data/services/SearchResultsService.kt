package com.malwinder.example.data.services

import com.malwinder.example.data.endpoints.StackOverflowApi
import com.malwinder.example.domain.models.SearchResult
import com.malwinder.example.utils.networking.NetworkTools
import io.reactivex.Single
import retrofit2.Response

class SearchResultsService {
    var api : StackOverflowApi = NetworkTools.retrofit.create(StackOverflowApi::class.java)

    fun getResults(@Suppress("SpellCheckingInspection") intitle : String, page : Int, pageSize : Int) : Single<Response<SearchResult>> {
        return api.getSearchResults(page, pageSize, intitle)
    }
}