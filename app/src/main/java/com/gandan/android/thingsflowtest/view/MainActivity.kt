package com.gandan.android.thingsflowtest.view

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.gandan.android.thingsflowtest.*
import com.gandan.android.thingsflowtest.model.RepoOrgModel
import com.gandan.android.thingsflowtest.repository.IssueListDataRepository
import com.gandan.android.thingsflowtest.viewmodel.MainViewModel
import com.gandan.android.thingsflowtest.viewmodel.MainViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.dialog_org_repo.view.*
import javax.inject.Inject

class MainActivity: AppCompatActivity() {


    @Inject
    lateinit var viewModel: MainViewModel

    @Inject
    lateinit var issueListDataRepository: IssueListDataRepository

    @Inject
    lateinit var sharedPreferences: SharedPreferences
    @Inject
    lateinit var editor: SharedPreferences.Editor

    private lateinit var mainRecyclerAdapter: MainRecyclerAdapter
    private lateinit var alertDialog: AlertDialog
    private lateinit var repoOrgModel: RepoOrgModel
    private var beforeRepoOrgModel = RepoOrgModel("google", "dagger")

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as BaseApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val org = sharedPreferences.getString("org", "google")?: "google"
        val repo = sharedPreferences.getString("repo", "dagger")?: "dagger"
        repoOrgModel = RepoOrgModel(org, repo)
        mainRecyclerAdapter =
            MainRecyclerAdapter(
                Glide.with(this),
                this
            )
        repoRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = mainRecyclerAdapter
        }

        viewModel = ViewModelProvider(this,
            MainViewModelFactory(
                issueListDataRepository
            )
        ).get(MainViewModel::class.java)
        viewModel.issueList.observe(this, Observer {
            mainRecyclerAdapter.setItemList(it)
            mainRecyclerAdapter.setRepoOrgModel(repoOrgModel)
            repoRecyclerView.visibility = View.VISIBLE
        })

        viewModel.networkStatusData.observe(this, Observer {
            if(it !== "Loading" && it !== "Success") {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
                repoOrgModel = beforeRepoOrgModel
                setView()
            } else if (it === "Success"){
                beforeRepoOrgModel = repoOrgModel
                editor.putString("org", repoOrgModel.org)
                editor.putString("repo", repoOrgModel.repo)
                editor.apply()
            }
        })

        setView()
        setDialog()

        repoTitle.setOnClickListener{
            alertDialog.show()
        }
    }

    private fun setView() {
        repoRecyclerView.visibility = View.GONE
        viewModel.setRepoOrgData(repoOrgModel)
        repoTitle.text = getString(R.string.repo_title, repoOrgModel.org, repoOrgModel.repo)
    }

    private fun setDialog() {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_org_repo, null)
        alertDialog = AlertDialog.Builder(this).setView(dialogView).setPositiveButton(getString(R.string.search)
        ) { dialog, which ->
            val org = dialogView.orgEditText.text.toString()
            val repo = dialogView.repoEditText.text.toString()
            repoOrgModel = RepoOrgModel(org, repo)
            setView()
            dialog.dismiss()
             }.setNegativeButton(getString(R.string.cancel)) { dialog, which -> dialog.dismiss() }.create()
    }
}