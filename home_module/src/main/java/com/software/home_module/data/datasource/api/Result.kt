package com.software.home_module.datasource.api

import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("name")
    val name: String?,
    @SerializedName("url")
    val url: String?
)