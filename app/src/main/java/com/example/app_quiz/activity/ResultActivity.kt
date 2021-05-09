package com.example.app_quiz.activity

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.util.Log
import com.example.app_quiz.R

import com.example.app_quiz.models.Quiz
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_result.*

class ResultActivity : AppCompatActivity() {
    lateinit var  quiz : Quiz
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        setUpView()
        scrollView.setOnClickListener {
            val intent = Intent(this, EndActivity::class.java )
            startActivity(intent)
        }
    }

    private fun setUpView() {
        val quizData  = intent.extras?.getString("quiz")
        //Converting from JSON String to a Data Class
        quiz = Gson().fromJson<Quiz>(quizData,Quiz::class.java)
        calculateScore()
        setAnswerView()
    }

    private fun setAnswerView() {
        val builder = StringBuilder("")
        for (entry in quiz.questions.entries) {
            val question = entry.value
            // noi chuỗi
            //<font></font>: dùng để thay đổi  màu chữ.
            //Thẻ <br>: xuống dòng trong văn bản.
            //<b></b>, <strong></strong>: im đậm chữ.
            builder.append("<font color'#F44336'><b>Question: ${question.description}</b></font><br/><br/>")
            builder.append("<font color='#76FF03'>Answer: ${question.answers}</font><br/><br/>")
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                //FROM_HTML_MODE_COMPACT  Phân tách các phần tử cấp khối bằng dấu ngắt dòng (một ký tự dòng mới) ở giữa.
                txtAnswer.text = Html.fromHtml(builder.toString(), Html.FROM_HTML_MODE_COMPACT);
            } else {
                txtAnswer.text = Html.fromHtml(builder.toString());
            }

    }

    private fun calculateScore() {
        var score  = 0

        for(entry  in quiz.questions.entries){
            val question  = entry.value
            if(question.answers == question.userAnswer){
                score += 10
            }
        }
        txtScore.text = "Your Score : $score"
        Log.d("diem",score.toString())
    }
}