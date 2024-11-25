package com.example.myapplicationtest

import android.annotation.SuppressLint
import android.os.Bundle
import android.transition.Scene
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    // Текущая сцена
    private var currentSceneIndex = 0

    // Список сцен
    private val scenes = listOf(
        Scene(
            backgroundRes = R.drawable.scene1,
            text = "Once upon a time, in a faraway land..."
        ),
        Scene(
            backgroundRes = R.drawable.scene2,
            text = "The hero began their journey through the mysterious forest."
        ),
        Scene(
            backgroundRes = R.drawable.scene3,
            text = "A strange figure appeared in the shadows."
        )
    )

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val backgroundImageView = findViewById<ImageView>(R.id.backgroundImageView)
        val textView = findViewById<TextView>(R.id.textView)
        val nextButton = findViewById<Button>(R.id.nextButton)

        // Установка начальной сцены
        updateScene(backgroundImageView, textView)

        // Кнопка для перехода к следующей сцене
        nextButton.setOnClickListener {
            if (currentSceneIndex < scenes.size - 1) {
                currentSceneIndex++
                updateScene(backgroundImageView, textView)
            } else {
                // Действие при завершении
                textView.text = "The End."
                nextButton.isEnabled = false
            }
        }
    }

    // Обновление фона и текста
    private fun updateScene(backgroundImageView: ImageView, textView: TextView) {
        val scene = scenes[currentSceneIndex]
        backgroundImageView.setImageResource(scene.backgroundRes)
        textView.text = scene.text
    }

}


