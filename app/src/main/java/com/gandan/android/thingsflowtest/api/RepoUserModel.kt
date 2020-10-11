package com.gandan.android.thingsflowtest.api

import com.google.gson.annotations.SerializedName

data class RepoUserModel(
    //login == user nickname
    val login: String,
    @SerializedName("avatar_url")
    val avatarUrl: String
)