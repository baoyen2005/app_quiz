package com.example.app_quiz.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.app_quiz.R
import com.example.app_quiz.models.Question

class OptionAdapter (val context: Context, val question: Question ):
        RecyclerView.Adapter<OptionAdapter.optionViewHolder>() {

    private var options: List<String> = listOf(question.option1,
            question.option2,
        question.option3,question.option4)

    inner class optionViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var optionView = itemView.findViewById<TextView>(R.id.quiz_option)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): optionViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.option_item,
                parent,false)
        return optionViewHolder(view)
    }
// t ko hiểu lắm chỗ này , dùng để set giá trị cho mỗi view à b
    // có thể hiểu là nv
    override fun onBindViewHolder(holder: optionViewHolder, position: Int) {
        holder.optionView.text = options[position]
        holder.itemView.setOnClickListener{
            //Toast.makeText(context,options[position],Toast.LENGTH_SHORT).show()
            question.userAnswer = options[position]
            notifyDataSetChanged()
        }
     if(question.userAnswer == options[position]){
        holder.itemView.setBackgroundResource(R.drawable.option_item_selected_bg)
    }
    else {
        holder.itemView.setBackgroundResource(R.drawable.option_item_bg)
     }


    }

    override fun getItemCount(): Int {
        return options.size
    }
}