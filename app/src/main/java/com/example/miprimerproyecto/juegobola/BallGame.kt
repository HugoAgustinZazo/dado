package com.example.miprimerproyecto.juegobola

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.miprimerproyecto.R
import com.example.miprimerproyecto.databinding.ActivityBallGameBinding

class BallGame : AppCompatActivity() {
    lateinit var binding: ActivityBallGameBinding
    private lateinit var game: GameV
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBallGameBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        setContentView(R.layout.activity_ball_game)

        game = GameV(this)
        setContentView(game)
    }

    override fun onResume() {
        super.onResume()
        game.resume()
    }

    override fun onPause() {
        super.onPause()
        game.pause()
    }

}