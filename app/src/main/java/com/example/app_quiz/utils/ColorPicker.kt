package com.example.app_quiz.utils

import android.graphics.Color


/*
val: read-only (chỉ để đọc) chỉ khỏi tạo getter,
với var: mutable (có thể thay đổi được): khởi tạo getter và setter
 */
object ColorPicker {
    val color =  arrayOf(

            "#FFFF8D",
            "#69F0AE"
            )
    var currentColorIndex = 0
    fun getColor():String{
        currentColorIndex = (currentColorIndex+1)% color.size
        return color[currentColorIndex]
    }

}
