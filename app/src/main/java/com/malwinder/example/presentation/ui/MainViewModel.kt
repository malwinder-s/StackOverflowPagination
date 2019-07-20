package com.malwinder.example.presentation.ui

import com.malwinder.example.domain.models.SearchResultItem
import com.malwinder.example.presentation.ui.base.BasePaginationViewModel
import com.malwinder.example.utils.pagination.factory.SearchResultDataSourceFactory

class MainViewModel : BasePaginationViewModel<SearchResultDataSourceFactory, SearchResultItem>() {
    init {
        dataSourceFactory = SearchResultDataSourceFactory(getListener(), null)
    }

    override fun getPageSize(): Int = 3

    /**
     * Handles a new user search
     */
    fun newSearch(intitle: String) {
        dataSourceFactory.intitle = intitle
        clearData()
    }
}