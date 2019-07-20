package com.malwinder.example.domain.mappers

import com.malwinder.example.domain.models.SearchResult
import org.mapstruct.Mapper
import org.mapstruct.factory.Mappers

@Mapper
interface SearchResultMapper {
    companion object {
        val Instance = Mappers.getMapper(SearchResultMapper::class.java)!!
    }

    fun map(results : SearchResult?) : SearchResult
}