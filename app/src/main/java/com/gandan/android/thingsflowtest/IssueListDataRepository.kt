package com.gandan.android.thingsflowtest

import androidx.lifecycle.LiveData
import javax.inject.Inject

class IssueListDataRepository @Inject constructor(private val apiService: APIService) {

    fun getIssueListData(): LiveData<ArrayList<RepoModel>> {
        val dataSource = IssueListDataSource(apiService)
        return dataSource.getIssueList()
    }
}