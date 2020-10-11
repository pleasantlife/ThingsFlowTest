package com.gandan.android.thingsflowtest.datasource

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.gandan.android.thingsflowtest.api.APIService
import com.gandan.android.thingsflowtest.api.RepoModel
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class IssueItemDataSource @Inject constructor(private val apiService: APIService) {

    val networkStatus: MutableLiveData<String> = MutableLiveData()

    fun getIssueItem(org: String, repo: String, number: Int): LiveData<RepoModel> {
        networkStatus.postValue("Loading")
        val result = MutableLiveData<RepoModel>()
        apiService.getIssue(org, repo, number).subscribeOn(Schedulers.io()).subscribe({
            networkStatus.postValue("Success")
          result.postValue(it)
        },{
            networkStatus.postValue(it.localizedMessage)
        })
        return result
    }
}