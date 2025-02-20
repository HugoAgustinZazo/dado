package com.example.miprimerproyecto.juegobola

import android.media.AudioAttributes
import android.media.SoundPool
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.miprimerproyecto.R
import com.example.miprimerproyecto.databinding.ActivityBallGameBinding

class BallGame : AppCompatActivity() {
    lateinit var binding: ActivityBallGameBinding
    private lateinit var game: GameV
    private var playing = false
    private var empezar = true
    private lateinit var soundPool: SoundPool
    private var soundId = 0
    private var streamId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBallGameBinding.inflate(layoutInflater)
        game = GameV(this, binding.surfaceView)
        setContentView(binding.root)
        soundPool = SoundPool.Builder().setMaxStreams(1).setAudioAttributes(
            AudioAttributes.Builder().setUsage(AudioAttributes.USAGE_MEDIA).setContentType(
                AudioAttributes.CONTENT_TYPE_MUSIC).build()
        ).build()
        soundId = soundPool.load(this,R.raw.musica,1)

        soundPool.setOnLoadCompleteListener{ _, sampleId, _ ->
            if (sampleId==soundId){
                playMusic()
            }
        }

        binding.button15.setOnClickListener(){
                 if(empezar){
                     empezar = false
                     game.resume()
                 }
                if (playing) {
                    game.pause()
                    playing = false
                    binding.button15.text = "Resume Game"
                } else {
                    game.resume()
                    playing = true
                    binding.button15.text = "Pause Game"
                }


            }

        binding.button16.setOnClickListener(){


        }


    }
    private fun playMusic(){
        streamId = soundPool.play(soundId, 1f, 1f, 1, -1, 1f)
    }
    override fun onDestroy() {
        super.onDestroy()
        soundPool.release()
    }

}