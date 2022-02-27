package com.example.toyapp.model

import com.google.gson.annotations.SerializedName

data class ApiUser(
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("name")
    val name: String = "",
    @SerializedName("hobby")
    val hobby: String = "",
    @SerializedName("phone")
    val phone: String = "",
    )
