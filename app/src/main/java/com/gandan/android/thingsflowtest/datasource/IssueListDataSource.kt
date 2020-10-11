package com.gandan.android.thingsflowtest.datasource

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.gandan.android.thingsflowtest.api.APIService
import com.gandan.android.thingsflowtest.api.RepoModel
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class IssueListDataSource @Inject constructor (private val apiService: APIService) {

    val networkStatus: MutableLiveData<String> = MutableLiveData()

    fun getIssueList(org: String, repo: String): LiveData<ArrayList<RepoModel>> {
        networkStatus.postValue("Loading")
        val result = MutableLiveData<ArrayList<RepoModel>>()
        apiService.getIssueList(org, repo).subscribeOn(Schedulers.io()).subscribe({
            result.postValue(it)
            networkStatus.postValue("Success")
        },{
            result.postValue(ArrayList())
            networkStatus.postValue(it.localizedMessage)
        })
        return result
    }

}