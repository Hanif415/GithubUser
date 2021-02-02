package com.koshka.githubuser.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.koshka.githubuser.R
import com.koshka.githubuser.model.Users

class Adapter(private val listUser: ArrayList<Users>) : RecyclerView.Adapter<Adapter.ViewHolder>() {
    val onItemClickClickCallback : OnItemClickCallback? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.github_user_item, parent)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(listUser[position])


    override fun getItemCount(): Int = listUser.size


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(listUser: Users) {
            with(itemView) {
                itemView.setOnClickListener {
                    onItemClickClickCallback?.onItemClicked(listUser)
                }
            }
        }
    }
}

interface OnItemClickCallback {
    fun onItemClicked(user: Users)
}