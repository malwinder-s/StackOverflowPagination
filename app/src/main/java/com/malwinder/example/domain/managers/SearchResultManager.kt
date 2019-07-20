package com.malwinder.example.domain.managers

import com.malwinder.example.data.services.SearchResultsService
import com.malwinder.example.domain.mappers.SearchResultMapper
import com.malwinder.example.domain.models.SearchResult
import io.reactivex.Single

/**
 * Class that connects the Data layer to Presentation, where the API objects are manipulated and observed by
 * the Views (Activity, Fragment or View)
 */
class SearchResultManager {
    var resultsService : SearchResultsService = SearchResultsService()

    fun getSearchResult(intitle : String, page : Int, pageSize : Int) : Single<SearchResult> {
        return resultsService.getResults(intitle, page, pageSize)
                // By calling `onErrorResumeNext` we could apply our own error handling function
                .onErrorResumeNext { throwable -> Single.error(throwable)}
                // Since we are using Retrofit's Response, we will need to parse it and check
                // if the response was successful or not
                .flatMap { response ->
                    if (!response.isSuccessful) {
                        Single.error(Throwable(response.code().toString()))
                    } else Single.just(response)
                }
                // If the response is successful, we retrieve its body
                .map {response -> response.body()}
    }
}