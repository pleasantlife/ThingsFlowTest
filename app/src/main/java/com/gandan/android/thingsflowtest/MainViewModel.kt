package com.gandan.android.thingsflowtest

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class MainViewModel @Inject constructor(private val issueListDataRepository: IssueListDataRepository) : ViewModel() {

    val issueList: LiveData<ArrayList<RepoModel>> by lazy {
        issueListDataRepository.getIssueListData()
    }
}