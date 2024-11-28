package com.example.myapplicationtest

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class NovelActivity : AppCompatActivity() {
    private var sceneIndex = 0
    private val scenes = listOf(
        Pair(R.drawable.bg, "Добро пожаловать в первый этап новеллы!"),
        Pair(R.drawable.i, "Здесь начинается приключение."),
        Pair(R.drawable.i, "Конец истории!"),
        Pair(R.drawable.i, "точно конец")
    )



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_novel_dinamic)

        window.decorView.systemUiVisibility = (
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        or View.SYSTEM_UI_FLAG_FULLSCREEN
                        or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                )

        val backgroundImage = findViewById<ImageView>(R.id.backgroundImage)
        val novelText = findViewById<TextView>(R.id.novelText)
        val nextButton = findViewById<Button>(R.id.nextButton)

        val prefs = getSharedPreferences("NovelPrefs", Context.MODE_PRIVATE)
        sceneIndex = prefs.getInt("LastSceneIndex", 0)

        updateScene(backgroundImage, novelText)

        nextButton.setOnClickListener {
            if (sceneIndex < scenes.size - 1) {
                sceneIndex++
                updateScene(backgroundImage, novelText)

                saveSceneIndex()
            } else {
                resetAndReturnToMainMenu()
            }
        }
    }

    private fun updateScene(backgroundImage: ImageView, novelText: TextView) {
        backgroundImage.setImageResource(scenes[sceneIndex].first)
        novelText.text = scenes[sceneIndex].second
    }

    private fun saveSceneIndex() {
        val prefs = getSharedPreferences("NovelPrefs", Context.MODE_PRIVATE)
        with(prefs.edit()) {
            putInt("LastSceneIndex", sceneIndex)
            apply()
        }
    }

    private fun resetAndReturnToMainMenu() {
        // Сбрасываем индекс сцены
        val prefs = getSharedPreferences("NovelPrefs", Context.MODE_PRIVATE)
        with(prefs.edit()) {
            putInt("LastSceneIndex", 0)
            apply()
        }
        // Возвращаемся в главное меню
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}
