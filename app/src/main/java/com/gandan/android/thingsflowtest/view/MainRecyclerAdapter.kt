package com.gandan.android.thingsflowtest.view

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.gandan.android.thingsflowtest.R
import com.gandan.android.thingsflowtest.api.RepoModel
import com.gandan.android.thingsflowtest.model.RepoOrgModel
import kotlinx.android.synthetic.main.image_item.view.*
import kotlinx.android.synthetic.main.repo_item.view.*

class MainRecyclerAdapter(private val requestManager: RequestManager, private val context: Context): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val repoViewType = -1
    private val imageViewType = -2
    private val bannerPosition = 4

    private var repoItemList = ArrayList<RepoModel>()
    private lateinit var repoOrgModel: RepoOrgModel

    fun setItemList(itemList: ArrayList<RepoModel>) {
        this.repoItemList = itemList
        notifyDataSetChanged()
    }

    fun setRepoOrgModel(repoOrgModel: RepoOrgModel) {
        this.repoOrgModel = repoOrgModel
    }

    override fun getItemViewType(position: Int): Int {
        return if(position == bannerPosition) {
            imageViewType
        } else {
            repoViewType
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if(viewType == repoViewType) {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.repo_item, parent, false)
            RepoHolder(view)
        } else {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.image_item, parent, false)
            ImageHolder(view)
        }
    }

    override fun getItemCount(): Int {
        return repoItemList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is RepoHolder) {
            holder.bind(repoItemList[position])
        } else {
            (holder as ImageHolder).bind()
        }
    }

    inner class RepoHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(repoModel: RepoModel) {
            itemView.issueText.text = context.getString(R.string.issue_list, repoModel.number, repoModel.title)
            itemView.setOnClickListener {
                val intent = Intent(context, DetailActivity::class.java)
                intent.putExtra("org", repoOrgModel.org)
                intent.putExtra("repo", repoOrgModel.repo)
                intent.putExtra("number", repoModel.number)
                context.startActivity(intent)
            }
        }
    }

    inner class ImageHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        private val thingsFlowWebUrl = "https://thingsflow.com/ko/home"

        fun bind(){
            requestManager.load(R.drawable.main_logo)
                .into(itemView.logoImageView)
            itemView.setOnClickListener {
                context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(thingsFlowWebUrl)))
            }
        }
    }
}