package com.example.factsapp.model


import com.google.gson.annotations.SerializedName


data class Fact(
    @SerializedName("rows")
    var rows: List<Row>?,
    @SerializedName("title")
    var title: String?
)