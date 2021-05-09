package com.example.app_quiz.models

import java.io.FileDescriptor

data class Question(
        val description: String ="",
        val option1: String ="",
        val option2: String ="",
        val option3: String ="",
        val option4: String ="",
        val answers: String ="",
        var userAnswer: String =""

)