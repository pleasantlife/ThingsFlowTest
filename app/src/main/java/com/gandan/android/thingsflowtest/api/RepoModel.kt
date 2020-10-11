package com.gandan.android.thingsflowtest.api

data class RepoModel(
    val number: Int,
    val title: String,
    val user: RepoUserModel,
    val body: String
)