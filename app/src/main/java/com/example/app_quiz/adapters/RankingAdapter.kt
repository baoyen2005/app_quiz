package com.example.app_quiz.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.app_quiz.R
import com.example.app_quiz.models.User

class RankingAdapter(val context: Context,private var rankings: MutableList<User>) :
    RecyclerView.Adapter<RankingAdapter.VHolder>() { // r code di

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VHolder {
        val view = LayoutInflater.from(context).inflate(
            R.layout.ranking_item,
            parent, false
        )
        return VHolder(view)
    }
    //rooif do viet tiep di//tu tu


    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: VHolder, position: Int) {
        holder.rank_option.text = ((position + 1).toString())+" "+ rankings.get(position).userName+" "+ rankings.get(position).yourScore
    }
    //chac la chi nhu nay thoi ^^
    // con cai sort thi lam ow main a b
    override fun getItemCount(): Int {
        return rankings?.size ?: 0

    }

    inner class VHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var rank_option: TextView = itemView.findViewById(R.id.rank_option)

    }


}