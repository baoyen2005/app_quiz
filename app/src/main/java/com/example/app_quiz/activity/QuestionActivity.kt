package com.example.app_quiz.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.app_quiz.R
import com.example.app_quiz.adapters.OptionAdapter
import com.example.app_quiz.models.Question
import com.example.app_quiz.models.Quiz
import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_question.*
import java.util.*

class QuestionActivity : AppCompatActivity() {
    var quizzes: MutableList<Quiz>? = null
    var questions: MutableMap<String, Question>? = null
    var index = 1


    var type: String = ""

    //phần countdown này// bạn khai báo riêng ra đc k
    // phần này ko phải t code

    lateinit var questionTime: CountDownTimer


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)
        type = intent.extras?.getString("qs_type")!!
        setUpFirestore()
        setUpEventListener()


        questionTime = object : CountDownTimer(15000, 1000) {
            override fun onTick(p0: Long) {
                txt_sec_remain.text = (p0 / 1000).toString()

            }

            override fun onFinish() {
                questionTime.cancel()
                index++
                if (index != questions!!.size + 1) bindViews() //next question
                else btnSubmit.performClick() //last question
            }
        }


    }

    private fun setUpEventListener() {
        btnNext.setOnClickListener {
            questionTime.cancel()
            index++

            bindViews()
        }
        btnSubmit.setOnClickListener {
            questionTime.cancel()
            Log.d("Finalquiz", questions.toString())
            val intent = Intent(this, ResultActivity::class.java)
            //convert objects of TestModel class to JSON
            val json = Gson().toJson(quizzes!![0])
            intent.putExtra("quiz", json)
            startActivity(intent)
            finish()
        }
    }

    private fun setUpFirestore() {

        val firestore = FirebaseFirestore.getInstance()
        if (type == "questions") {
            Log.d("QsActivity", type)
            firestore.collection("quiz").whereEqualTo("title", "questions")
                .get()
                .addOnSuccessListener {
                    if (it != null && !it.isEmpty) {
                        quizzes = it.toObjects(Quiz::class.java)
                        questions = quizzes!![0].questions
                        bindViews()

                    }
                }
        }
//        else
//        {
//            Log.d("QsActivity",type)
//            firestore.collection("quiz")
//                .whereEqualTo("title","average questions")
//                .get()
//                .addOnSuccessListener {
//                    if(it != null && !it.isEmpty){
//                        quizzes = it.toObjects(Quiz::class.java)
//                        questions = quizzes!![0].questions
//
//                        bindViews()
//
//                    }
//                }
//        }


    }

    private fun bindViews() {
        btnPrevious.visibility = View.GONE
        btnNext.visibility = View.GONE
        btnSubmit.visibility = View.GONE
        if (index == 1) { // first question
            btnNext.visibility = View.VISIBLE
        } else if (index == questions!!.size) { //lastquestion
            btnSubmit.visibility = View.VISIBLE
            //btnPrevious.visibility = View.VISIBLE
        } else {
            btnNext.visibility = View.VISIBLE
            //btnPrevious.visibility = View.VISIBLE
        }
        val question = questions!!["question$index"]
        question?.let {
            description.text = question.description
            val optionAdapter = OptionAdapter(this, it)
            optionList.layoutManager = LinearLayoutManager(this)
            optionList.adapter = optionAdapter
            optionList.setHasFixedSize(true)

        }
        txtquestion.text = index.toString() +"/"+ questions!!.size.toString()
        questionTime.start()
    }

}