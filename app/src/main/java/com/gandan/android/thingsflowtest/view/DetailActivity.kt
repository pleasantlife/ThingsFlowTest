package com.gandan.android.thingsflowtest.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.gandan.android.thingsflowtest.*
import com.gandan.android.thingsflowtest.model.RepoOrgNumModel
import com.gandan.android.thingsflowtest.repository.IssueItemDataRepository
import com.gandan.android.thingsflowtest.viewmodel.DetailViewModel
import com.gandan.android.thingsflowtest.viewmodel.DetailViewModelFactory
import kotlinx.android.synthetic.main.activity_detail.*
import javax.inject.Inject

class DetailActivity : AppCompatActivity() {

    private lateinit var viewModel: DetailViewModel

    @Inject
    lateinit var issueItemDataRepository: IssueItemDataRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as BaseApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        viewModel = ViewModelProvider(this,
            DetailViewModelFactory(
                issueItemDataRepository
            )
        ).get(DetailViewModel::class.java)

        val org = intent.getStringExtra("org")?: ""
        val repo = intent.getStringExtra("repo")?: ""
        val number = intent.getIntExtra("number", -1)
        val dataModel = RepoOrgNumModel(org, repo, number)

        viewModel.setData(dataModel)

        viewModel.repoItem.observe(this, Observer {
            supportActionBar?.title = getString(R.string.issue_title, it.number)
            Glide.with(this).load(it.user.avatarUrl).into(avatarImage)
            userNameText.text = it.user.login
            issueBodyText.text = it.body
        })

        viewModel.networkStatus.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })


    }
}