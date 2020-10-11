package com.gandan.android.thingsflowtest

import com.google.gson.annotations.SerializedName

data class RepoModel(
    val number: Int,
    val title: String,
    val user: RepoUserModel,
    val body: String
)