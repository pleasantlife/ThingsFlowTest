package com.gandan.android.thingsflowtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity: AppCompatActivity() {


    @Inject
    lateinit var viewModel: MainViewModel

    @Inject
    lateinit var issueListDataRepository: IssueListDataRepository
    private lateinit var mainRecyclerAdapter: MainRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as BaseApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainRecyclerAdapter = MainRecyclerAdapter(Glide.with(this), this)
        repoRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = mainRecyclerAdapter
        }

        viewModel = ViewModelProvider(this, ViewModelFactory(issueListDataRepository)).get(MainViewModel::class.java)
        viewModel.issueList.observe(this, Observer {
            mainRecyclerAdapter.setItemList(it)
        })
    }
}