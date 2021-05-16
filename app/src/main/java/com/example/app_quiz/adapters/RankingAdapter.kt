package com.example.app_quiz.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.app_quiz.R
import com.example.app_quiz.models.User
import com.example.app_quiz.utils.RankIcon

class RankingAdapter(val context: Context,private var rankings: MutableList<User>) :
    RecyclerView.Adapter<RankingAdapter.VHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VHolder {
        val view = LayoutInflater.from(context).inflate(
            R.layout.ranking_item,
            parent, false
        )
        return VHolder(view)
    }



    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: VHolder, position: Int) {
       if(position > 17){
           holder.rank_option.text = ((position + 1).toString())+"   "+ rankings.get(position).userName

           holder.score.text = rankings.get(position).yourScore.toString()
           holder.iconRank.setImageResource(R.drawable.smile)

       }
       else{
           holder.rank_option.text = rankings.get(position).userName
           holder.score.text = rankings.get(position).yourScore.toString()
           holder.iconRank.setImageResource(RankIcon.getRank())
       }
    }

    override fun getItemCount(): Int {
        return rankings?.size ?: 0

    }

    inner class VHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var rank_option: TextView = itemView.findViewById(R.id.rank_option)
        var iconRank : ImageView = itemView.findViewById(R.id.imgRank)
        var score : TextView = itemView.findViewById(R.id.txtYourScore)

    }


}