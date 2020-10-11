package com.gandan.android.thingsflowtest.repository

import androidx.lifecycle.LiveData
import com.gandan.android.thingsflowtest.datasource.IssueItemDataSource
import com.gandan.android.thingsflowtest.api.APIService
import com.gandan.android.thingsflowtest.api.RepoModel
import javax.inject.Inject

class IssueItemDataRepository @Inject constructor(apiService: APIService) {

    private val issueItemDataSource = IssueItemDataSource(apiService)

    fun getIssueItem(org:String, repo:String, number: Int): LiveData<RepoModel> {
        return issueItemDataSource.getIssueItem(org, repo, number)
    }

    fun getNetworkStatus(): LiveData<String> {
        return issueItemDataSource.networkStatus
    }
}