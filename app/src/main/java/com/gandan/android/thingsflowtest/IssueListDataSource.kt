package com.gandan.android.thingsflowtest

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class IssueListDataSource @Inject constructor (private val apiService: APIService) {


    fun getIssueList(): LiveData<ArrayList<RepoModel>> {
        val result = MutableLiveData<ArrayList<RepoModel>>()
        apiService.getIssueList("google", "dagger").subscribeOn(Schedulers.io()).subscribe({
            result.postValue(it)
        },{
            Log.e("Error!", "${it.message}")
        })
        return result
    }

}