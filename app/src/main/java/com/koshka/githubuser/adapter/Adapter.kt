package com.koshka.githubuser.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.koshka.githubuser.R
import com.koshka.githubuser.model.UsersItem

class Adapter(private val listUser: List<UsersItem?>) :
    RecyclerView.Adapter<Adapter.ViewHolder>() {
    val onItemClickClickCallback: OnItemClickCallback? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.github_user_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(listUser[position])


    override fun getItemCount(): Int = listUser.size


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvName = itemView.findViewById<TextView>(R.id.tv_name)
        private val cvAvatar = itemView.findViewById<ImageView>(R.id.img_avatar)
        fun bind(user: UsersItem?) {
            with(itemView) {
                tvName.text = user?.login.toString()
                Glide.with(this).load(user?.avatarUrl).into(cvAvatar)
                itemView.setOnClickListener {
                    onItemClickClickCallback?.onItemClicked(user)
                }
            }
        }
    }
}

interface OnItemClickCallback {
    fun onItemClicked(user: UsersItem?)
}