package com.gandan.android.thingsflowtest

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import kotlinx.android.synthetic.main.image_item.view.*
import kotlinx.android.synthetic.main.repo_item.view.*

class MainRecyclerAdapter(private val requestManager: RequestManager, private val context: Context): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val repoViewType = -1
    val imageViewType = -2

    var repoItemList = ArrayList<RepoModel>()

    fun setItemList(itemList: ArrayList<RepoModel>) {
        this.repoItemList = itemList
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return if(position == 4) {
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
        }
    }

    inner class ImageHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        val thingsFlowWebUrl = "https://thingsflow.com/ko/home"

        fun bind(){
            requestManager.load(R.drawable.main_logo)
                .into(itemView.logoImageView)
            itemView.setOnClickListener {
                context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(thingsFlowWebUrl)))
            }
        }
    }
}