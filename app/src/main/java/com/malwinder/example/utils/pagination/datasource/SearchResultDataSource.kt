package com.malwinder.example.utils.pagination.datasource

import android.annotation.SuppressLint
import com.malwinder.example.domain.managers.SearchResultManager
import com.malwinder.example.domain.models.SearchResult
import com.malwinder.example.domain.models.SearchResultItem
import com.malwinder.example.utils.pagination.datasource._base.BaseDataSource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Class that handles how to retrieve data for the recyclerview
 * @see BaseDataSource
 */
class SearchResultDataSource(var intitle: String) : BaseDataSource<SearchResultItem>() {

    val manager: SearchResultManager = SearchResultManager()

    @SuppressLint("CheckResult")
    override fun loadInitialData(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, SearchResultItem>) {
        // in the initial load, we will start at page 0, and retrieve the number of pages in the params.requestLoadSize
        manager.getSearchResult(intitle, 1, params.requestedLoadSize)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(this::addDisposable)
                .subscribe(
                        { searchResult ->
                            searchResult.items?.filterNotNull()?.let {
                                submitInitialData(it, params, callback)
                            }
                        },
                        { error -> submitInitialError(error) }
                )
    }

    @SuppressLint("CheckResult")
    override fun loadAditionalData(params: LoadParams<Int>, callback: LoadCallback<Int, SearchResultItem>) {
        manager.getSearchResult(intitle, params.key, params.requestedLoadSize)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(this::addDisposable)
                .subscribe(
                        { searchResult ->
                            searchResult.items?.filterNotNull()?.let {
                                submitData(it, params, callback)
                            }
                        },
                        { error -> submitError(error) }
                )
    }
}