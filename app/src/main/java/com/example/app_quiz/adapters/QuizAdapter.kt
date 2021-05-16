package com.example.app_quiz.adapters

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.app_quiz.R
import com.example.app_quiz.activity.QuestionActivity
import com.example.app_quiz.models.Question
import com.example.app_quiz.models.Quiz
import com.example.app_quiz.utils.ColorPicker
import com.example.app_quiz.utils.IconPiker

/*
Còn Adapter trong Android mang trách nhiệm chuyển đổi dữ liệu,
 từ dữ liệu thô ở đầu vào (mà mình gọi là DataSource như hình bên dưới), thành dữ liệu hiển
 thị lên View mà người dùng có thể dễ dàng đọc, hiểu và tương tác.
 */
class QuizAdapter(val context: Context,val quizzes : List<Quiz>)
    : RecyclerView.Adapter<QuizAdapter.QuizViewHolder>(){
    // inner :Class con trong Kotlin


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuizViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.quiz_item,parent,false)
        return QuizViewHolder(view)
    }

    override fun onBindViewHolder(holder: QuizViewHolder, position: Int) {
        holder.textViewTitle.text = quizzes[position].title
       // holder.cardContainer.setCardBackgroundColor(Color.parseColor(ColorPicker.getColor()))
    //   holder.inconView.setImageResource(IconPiker.getIcon())
        holder.itemView.setOnClickListener{
            Toast.makeText(context,quizzes[position].title,Toast.LENGTH_SHORT).show()
            val intent = Intent(context,QuestionActivity::class.java)
            intent.putExtra("qs_type", quizzes[position].title)
//            intent.putExtra("DATE",quizzes[position].title)
            context.startActivity(intent)

        }
    }

    override fun getItemCount(): Int {
        return quizzes.size
    }
    inner class QuizViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var textViewTitle :TextView = itemView.findViewById(R.id.quizTitle)
        var inconView: ImageView = itemView.findViewById(R.id.quizIcon)
        var cardContainer: CardView = itemView.findViewById(R.id.cardContainer)


    }
}