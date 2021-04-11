package com.example.factsapp.model


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity(tableName = "facts_table")
data class Row(
    @PrimaryKey(autoGenerate = true)
    var rowId: Long = 0,
    @SerializedName("description")
    var description: String?,
    @SerializedName("imageHref")
    var imageHref: String?,
    @SerializedName("title")
    var title: String?
) {
    fun isNotNull(): Boolean {
        return this.title != null || this.description != null || this.imageHref != null
    }
}