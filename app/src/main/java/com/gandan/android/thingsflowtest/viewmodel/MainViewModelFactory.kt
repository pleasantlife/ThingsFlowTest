package com.gandan.android.thingsflowtest.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gandan.android.thingsflowtest.repository.IssueListDataRepository

class MainViewModelFactory(private val issueListDataRepository: IssueListDataRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(
            issueListDataRepository
        ) as T
    }
}