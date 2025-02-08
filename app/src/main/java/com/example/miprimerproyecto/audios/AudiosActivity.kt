package com.example.miprimerproyecto.audios

import android.media.AudioAttributes
import android.media.SoundPool
import android.os.Bundle
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.miprimerproyecto.R
import com.example.miprimerproyecto.databinding.ActivityAudiosBinding


class AudiosActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAudiosBinding
    private lateinit var soundPool: SoundPool
    private var audio1: Int = 0
    private var audio2: Int = 0
    private var audio3: Int = 0
    private var audioActivo: Int = -1
    private var isPlaying = true
    private var velocidadActual = 1f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAudiosBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(R.layout.activity_audios)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        soundPool = SoundPool.Builder().setMaxStreams(3).setAudioAttributes(AudioAttributes.Builder().setUsage(AudioAttributes.USAGE_MEDIA).setContentType(AudioAttributes.CONTENT_TYPE_MUSIC).build()).build()

        audio1 = soundPool.load(this,R.raw.audio1,1)
        audio2 = soundPool.load(this,R.raw.avatar,1)
        audio3 = soundPool.load(this,R.raw.audio3,1)


        binding.audio1.setOnClickListener(){
            pausarAudioActivo()
            isPlaying = true
            playAudio(audio1)
        }
        binding.audio2.setOnClickListener(){
            pausarAudioActivo()
            isPlaying = true
            playAudio(audio2)
        }
        binding.audio3.setOnClickListener(){
            pausarAudioActivo()
            isPlaying = true
            playAudio(audio3)
        }
        binding.pausar.setOnClickListener() {
            if (isPlaying) {
                soundPool.pause(audioActivo)
                binding.pausar.setText("Reanudar audio")
                isPlaying = false

            } else {
                soundPool.resume(audioActivo)
                binding.pausar.setText("Pausar audio")
                isPlaying = true
            }
        }
        binding.seekBar3.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progreso: Int, fromUser: Boolean) {
                if (audioActivo !== -1) {
                    val volumenizquierdo = 1.0f - (progreso / 100f)
                    val volumenderecho = progreso / 100f

                    soundPool.setVolume(audioActivo, volumenizquierdo, volumenderecho)
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {}

            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })
        binding.seekBar.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progreso: Int, fromUser: Boolean) {
                if (audioActivo !== -1) {
                    val volume = progreso / 100f
                    soundPool.setVolume(audioActivo, volume, volume)
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {}

            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })

        binding.seekBar2.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progreso: Int, fromUser: Boolean) {
                if (audioActivo !== -1) {
                    velocidadActual = 0.5f + (progreso / 100f)
                    soundPool.setRate(audioActivo, velocidadActual)
                }
            }
            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })


    }
    private fun playAudio(audio: Int) {
        if (audioActivo != 0) {
            audioActivo = soundPool.play(audio, 1f, 1f, 0, -1, velocidadActual)
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        soundPool.release()
    }
    private fun pausarAudioActivo(){
        if (audioActivo != -1) {
            soundPool.stop(audioActivo);
            audioActivo = -1;
        }
    }

}