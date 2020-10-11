package com.gandan.android.thingsflowtest.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.gandan.android.thingsflowtest.repository.IssueItemDataRepository
import com.gandan.android.thingsflowtest.api.RepoModel
import com.gandan.android.thingsflowtest.model.RepoOrgNumModel
import javax.inject.Inject

class DetailViewModel @Inject constructor(private val issueItemDataRepository: IssueItemDataRepository): ViewModel() {

    private val itemLiveData = MutableLiveData<RepoOrgNumModel>()
    fun setData(repoOrgNumModel: RepoOrgNumModel) {
        itemLiveData.postValue(repoOrgNumModel)
    }

    val repoItem: LiveData<RepoModel> by lazy {
        Transformations.switchMap(itemLiveData) {
            issueItemDataRepository.getIssueItem(it.org, it.repo, it.number)
        }
    }

    val networkStatus: LiveData<String> by lazy {
        issueItemDataRepository.getNetworkStatus()
    }
}