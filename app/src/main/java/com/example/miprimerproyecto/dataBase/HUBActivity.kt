package com.example.miprimerproyecto.dataBase

import android.content.Intent
import android.graphics.Color
import android.graphics.PorterDuff
import android.media.AudioAttributes
import android.media.AudioManager
import android.media.SoundPool
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.miprimerproyecto.R
import com.example.miprimerproyecto.appChistes.ChistesNorris
import com.example.miprimerproyecto.audios.AudiosActivity
import com.example.miprimerproyecto.camera.CameraHub
import com.example.miprimerproyecto.databinding.ActivityHubactivityBinding
import com.example.miprimerproyecto.firebaseapp.MainActivityFb
import com.example.miprimerproyecto.juegoDado.MainActivity
import com.example.miprimerproyecto.mediaPlayer.videos
import com.google.errorprone.annotations.Modifier
import com.squareup.picasso.Picasso


class HUBActivity : AppCompatActivity() {
    lateinit var binding: ActivityHubactivityBinding
    private lateinit var soundPool: SoundPool
    private var soundId = 0
    private var streamId = 0
    private var isPlaying = true


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHubactivityBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(R.layout.activity_hubactivity)
        setContentView(binding.root)
        ocultarAnimaciones()
        val username = intent.getStringExtra("username")
        val avatar = intent.getStringExtra("avatar")
        Picasso.get().load(avatar).into(binding.imageView13)

        binding.textView9.textSize = 25F
        binding.textView10.textSize = 20F
        binding.textView11.textSize = 20F
        binding.textView10.setText("Usuario: "+username)
        binding.imageView13.setOnClickListener(){
            binding.imageView13.visibility = View.GONE
            binding.saludo.visibility = View.VISIBLE
            Handler(Looper.getMainLooper()).postDelayed({
                binding.imageView13.visibility = View.VISIBLE
                binding.saludo.visibility = View.GONE
            }, 2000)

        }

        binding.reproductor.setOnClickListener(){
            val intent = Intent(this@HUBActivity, AudiosActivity::class.java)
            intent.putExtra("username", username)
            intent.putExtra("avatar",avatar)
            soundPool.release()
            startActivity(intent)
        }
        binding.imageView23.setOnClickListener(){
            binding.checkVideo.visibility = View.VISIBLE
            binding.imageView23.visibility = View.GONE
            Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this@HUBActivity, videos::class.java)
            intent.putExtra("username", username)
            intent.putExtra("avatar",avatar)
            soundPool.release()
            startActivity(intent)
            }, 1500)
            Handler(Looper.getMainLooper()).postDelayed({
                binding.imageView23.visibility = View.VISIBLE
                binding.checkVideo.visibility = View.GONE
            }, 2000)

        }

        binding.imageView21.setOnClickListener(){
            binding.checkCamera.visibility = View.VISIBLE
            binding.imageView21.visibility = View.GONE
            Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this@HUBActivity, CameraHub::class.java)
            intent.putExtra("username", username)
            intent.putExtra("avatar",avatar)
            soundPool.release()
            startActivity(intent)
        }, 1500)
            Handler(Looper.getMainLooper()).postDelayed({
                binding.imageView21.visibility = View.VISIBLE
                binding.checkCamera.visibility = View.GONE
            }, 2000)

        }
        binding.imageView18.setOnClickListener(){
            binding.checkFire.visibility = View.VISIBLE
            binding.imageView18.visibility = View.GONE
            Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this@HUBActivity, MainActivityFb::class.java)
            intent.putExtra("username", username)
            intent.putExtra("avatar",avatar)
            soundPool.release()
            startActivity(intent)
            }, 1500)
            Handler(Looper.getMainLooper()).postDelayed({
                binding.imageView18.visibility = View.VISIBLE
                binding.checkFire.visibility = View.GONE
            }, 2000)


        }
        binding.imageView10.setOnClickListener(){
            binding.imageView10.visibility = View.GONE
            binding.check.visibility = View.VISIBLE
            Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this@HUBActivity, MainActivity::class.java)
            soundPool.release()
            startActivity(intent)
            }, 1500)
            Handler(Looper.getMainLooper()).postDelayed({
                binding.imageView10.visibility = View.VISIBLE
                binding.check.visibility = View.GONE
            }, 2000)

        }

        binding.imageView17.setOnClickListener(){
            binding.imageView17.visibility = View.GONE
            binding.checkChuck.visibility = View.VISIBLE
            Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this@HUBActivity,ChistesNorris::class.java)
            soundPool.release()
            startActivity(intent)
        }, 1500)
            Handler(Looper.getMainLooper()).postDelayed({
                binding.imageView17.visibility = View.VISIBLE
                binding.checkChuck.visibility = View.GONE
            }, 2000)


        }

        soundPool = SoundPool.Builder().setMaxStreams(1).setAudioAttributes(
            AudioAttributes.Builder().setUsage(AudioAttributes.USAGE_MEDIA).setContentType(AudioAttributes.CONTENT_TYPE_MUSIC).build()
        ).build()
        soundId = soundPool.load(this,R.raw.musica,1)

        soundPool.setOnLoadCompleteListener{ _, sampleId, _ ->
            if (sampleId==soundId){
                playMusic()
            }
        }
        
        binding.musica.setOnClickListener(){
            if(isPlaying){
                soundPool.pause(streamId)
                binding.musica.setText("Reanudar musica")
                isPlaying = false

            }else{
                soundPool.resume(streamId)
                binding.musica.setText("Pausar musica")
                isPlaying = true
            }
        }
    }
    private fun ocultarAnimaciones(){
        binding.check.visibility = View.GONE
        binding.checkCamera.visibility = View.GONE
        binding.checkChuck.visibility = View.GONE
        binding.checkVideo.visibility = View.GONE
        binding.checkFire.visibility = View.GONE
        binding.imageView23.visibility = View.VISIBLE
        binding.imageView21.visibility = View.VISIBLE
        binding.imageView17.visibility = View.VISIBLE
        binding.imageView18.visibility = View.VISIBLE
        binding.imageView10.visibility = View.VISIBLE
    }
    private fun playMusic(){
        streamId = soundPool.play(soundId, 1f, 1f, 1, -1, 1f)
    }
    override fun onDestroy() {
        super.onDestroy()
        soundPool.release()
    }
}