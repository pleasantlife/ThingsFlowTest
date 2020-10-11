package com.gandan.android.thingsflowtest

import com.google.gson.annotations.SerializedName

data class RepoUserModel(
    val login: String,
    @SerializedName("avatar_url")
    val avatarUrl: String
)