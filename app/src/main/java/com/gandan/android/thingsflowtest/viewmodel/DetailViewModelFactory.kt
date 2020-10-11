package com.gandan.android.thingsflowtest.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gandan.android.thingsflowtest.repository.IssueItemDataRepository


class DetailViewModelFactory(private val issueItemDataRepository: IssueItemDataRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DetailViewModel(
            issueItemDataRepository
        ) as T
    }
}