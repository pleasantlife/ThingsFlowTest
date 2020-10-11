package com.gandan.android.thingsflowtest.repository

import androidx.lifecycle.LiveData
import com.gandan.android.thingsflowtest.datasource.IssueListDataSource
import com.gandan.android.thingsflowtest.api.APIService
import com.gandan.android.thingsflowtest.api.RepoModel
import javax.inject.Inject

class IssueListDataRepository @Inject constructor(apiService: APIService) {

    private val dataSource = IssueListDataSource(apiService)

    fun getIssueListData(org: String, repo: String): LiveData<ArrayList<RepoModel>> {
        return dataSource.getIssueList(org, repo)
    }

    fun getNetworkStatus(): LiveData<String> {
        return dataSource.networkStatus
    }
}