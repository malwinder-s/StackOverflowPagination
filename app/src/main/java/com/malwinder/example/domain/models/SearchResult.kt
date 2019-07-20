package com.malwinder.example.domain.models


import com.google.gson.annotations.SerializedName

data class SearchResult(
        @SerializedName("has_more")
    val hasMore: Boolean? = null,
        @SerializedName("items")
    val items: List<SearchResultItem?>? = null,
        @SerializedName("quota_max")
    val quotaMax: Int? = null,
        @SerializedName("quota_remaining")
    val quotaRemaining: Int? = null
)