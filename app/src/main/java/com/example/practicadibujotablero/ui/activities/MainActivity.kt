package com.example.practicadibujotablero.ui.activities

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.practicadibujotablero.R
import com.example.practicadibujotablero.ui.components.TableroView
import com.example.practicadibujotablero.ui.model.Tablero

class MainActivity : AppCompatActivity() {
    private lateinit var resetBtn: Button
    private lateinit var testButton: Button
    private lateinit var tableroView: TableroView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        tableroView = findViewById(R.id.tableroView)
        resetBtn = findViewById<Button>(R.id.resetBtn)
        testButton = findViewById(R.id.testBtn)

        testButton.setOnClickListener{
            resetToTest()
        }
        resetBtn.setOnClickListener {
            resetTablero()
        }

    }
    //it was under the moonlight looking at your skin, white as the snow
    //colder than my soul, it was that oh so wonderful night
    //when the true meaning of my life came itself to show
    //you looked the most perfect the night I made you die
    private fun resetTablero() {
        tableroView.reset()

    }

    //your filth knows no boundaries, you are not even worthy of death
    //only your existence could be so unholy, repugnant and blasphemous
    //that ven at rock bottom you find a way to dig
    private fun resetToTest() {
        tableroView.resetTest()
    }

}