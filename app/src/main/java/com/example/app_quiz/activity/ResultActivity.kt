package com.example.app_quiz.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.util.Log
import com.example.app_quiz.R

import com.example.app_quiz.models.Quiz
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_profile.*
import kotlinx.android.synthetic.main.activity_result.*

@Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class ResultActivity : AppCompatActivity() {
    lateinit var quiz: Quiz
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        setUpView()

        btnRank.setOnClickListener {
            val intent = Intent(this, RankingActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun setUpView() {
        val quizData = intent.extras?.getString("quiz")
        //Converting from JSON String to a Data Class
        quiz = Gson().fromJson<Quiz>(quizData, Quiz::class.java)
        calculateScore()
        setAnswerView()
    }

    private fun setAnswerView() {
        val builder = StringBuilder("")
        var ind = 0
        for (entry in quiz.questions.entries) {
            ind++
            val question = entry.value
            // noi chuỗi
            //<font></font>: dùng để thay đổi  màu chữ.
            //Thẻ <br>: xuống dòng trong văn bản.
            //<b></b>, <strong></strong>: im đậm chữ.
            builder.append("<font color'#FF000000'><b>- Question $ind: ${question.description}</b></font><br/><br/>")
            builder.append("<font color='#F44336'>+ Answer $ind: ${question.answers}</font><br/><br/>")
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            //FROM_HTML_MODE_COMPACT  Phân tách các phần tử cấp khối bằng dấu ngắt dòng (một ký tự dòng mới) ở giữa.
            txtAnswer.text = Html.fromHtml(builder.toString(), Html.FROM_HTML_MODE_COMPACT);
        } else {
            txtAnswer.text = Html.fromHtml(builder.toString());
        }

    }

    @SuppressLint("SetTextI18n")
    private fun calculateScore() {
        var score = 0

        for (entry in quiz.questions.entries) {
            val question = entry.value
            if (question.answers == question.userAnswer) {
                score += 10
            }
        }
        txtScore.text = "Your Score : $score"
        Log.d("diem", score.toString())

        //đây
        pushScore(score)

    }

    //test
    private fun pushScore(score: Int) {
        val firestore = FirebaseFirestore.getInstance()
        val auth: FirebaseAuth = FirebaseAuth.getInstance()
        val user = auth.currentUser
        var name: String = "" // varr no cho sua bien val vs car tjof cái nào đúng
        firestore.collection("user").document(user.uid).get()
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    name = "" + it.result.get("name").toString()
                    val map = hashMapOf(
                        "userName" to name,
                        "yourScore" to score,
                    )
                    if (user != null) { // còn chỗ này đang null tại chưa login
                        firestore.collection("score")
                            .document(user.uid)
                            .set(map)
                            .addOnCompleteListener {
                                Log.d(
                                    "TAG",
                                    "DocumentSnapshot successfully written!"
                                )
                            }
                    } else {
                        Log.d("TAG", "pushScore: Null")
                    }
                }
            }

    }
}