package com.malwinder.example.utils.pagination.factory

import androidx.paging.DataSource
import com.malwinder.example.domain.models.SearchResultItem
import com.malwinder.example.utils.pagination.datasource.SearchResultDataSource
import com.malwinder.example.utils.pagination.datasource._base.OnDataSourceLoading

/**
 * Factory class that handles the creation of DataSources
 */
class SearchResultDataSourceFactory(var loading: OnDataSourceLoading,
                                    var intitle: String?) : DataSource.Factory<Int, SearchResultItem>() {
    lateinit var source : SearchResultDataSource

    override fun create(): DataSource<Int, SearchResultItem> {
        // invalidate the previous data source, if available

//        if (::source.isInitialized && source != null) source.invalidate()

        // if we have a user, then create a data source
//        if (user != null) {
            source = SearchResultDataSource(intitle!!)
            source.onDataSourceLoading = loading
            return source
//        }
//        return null
    }
}