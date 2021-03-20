package com.thor.fruitstation.model.getdata

import com.google.gson.annotations.SerializedName

class ResponseGetKategori(

    @field:SerializedName("data")
    val data: List<DataKategori>? = null,

    @field:SerializedName("message")
    val message: String? = null,

    @field:SerializedName("isSuccess")
    val isSuccess: Boolean? = null
)