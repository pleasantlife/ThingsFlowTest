package com.gandan.android.thingsflowtest.api

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface APIService {

    @GET("{org}/{repo}/issues")
    fun getIssueList(@Path("org") org: String, @Path("repo") repo: String): Single<ArrayList<RepoModel>>

    @GET("{org}/{repo}/issues/{number}")
    fun getIssue(@Path("org") org: String, @Path("repo") repo: String, @Path("number") number: Int): Single<RepoModel>
}