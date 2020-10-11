package com.gandan.android.thingsflowtest.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.gandan.android.thingsflowtest.repository.IssueListDataRepository
import com.gandan.android.thingsflowtest.api.RepoModel
import com.gandan.android.thingsflowtest.model.RepoOrgModel
import javax.inject.Inject

class MainViewModel @Inject constructor(private val issueListDataRepository: IssueListDataRepository) : ViewModel() {

    private val repoOrgLiveData = MutableLiveData<RepoOrgModel>()
    fun setRepoOrgData(repoOrgModel: RepoOrgModel) {
        repoOrgLiveData.postValue(repoOrgModel)
    }

    val issueList: LiveData<ArrayList<RepoModel>> by lazy {
        Transformations.switchMap(repoOrgLiveData) {
            issueListDataRepository.getIssueListData(it.org, it.repo)
        }
    }

    val networkStatusData: LiveData<String> by lazy {
        issueListDataRepository.getNetworkStatus()
    }
}