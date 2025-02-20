package com.example.miprimerproyecto.juegobola

import android.content.Intent
import android.media.AudioAttributes
import android.media.SoundPool
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.example.miprimerproyecto.R
import com.example.miprimerproyecto.dataBase.HUBActivity
import com.example.miprimerproyecto.dataBase.LoginActivity
import com.example.miprimerproyecto.dataBase.User
import com.example.miprimerproyecto.dataBase.UsuariosDataBase
import com.example.miprimerproyecto.databinding.ActivityBallGameBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BallGame : AppCompatActivity() {
    lateinit var binding: ActivityBallGameBinding
    private lateinit var game: GameV
    private var playing = false
    private var empezar = true
    private lateinit var soundPool: SoundPool
    private var soundId = 0
    private var streamId = 0
    private lateinit var username:String
    private lateinit var avatar:String
    private var total:Int = 0
    private var record:Int = 0
    private var totalPartidas:Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBallGameBinding.inflate(layoutInflater)
        game = GameV(this, binding.surfaceView)
        setContentView(binding.root)

        username = intent.getStringExtra("username").toString()
        avatar = intent.getStringExtra("avatar").toString()
        soundPool = SoundPool.Builder().setMaxStreams(1).setAudioAttributes(
            AudioAttributes.Builder().setUsage(AudioAttributes.USAGE_MEDIA).setContentType(
                AudioAttributes.CONTENT_TYPE_MUSIC).build()
        ).build()
        soundId = soundPool.load(this,R.raw.musica3,1)

        soundPool.setOnLoadCompleteListener{ _, sampleId, _ ->
            if (sampleId==soundId){
                playMusic()
            }
        }
        val db = Room.databaseBuilder(applicationContext, UsuariosDataBase::class.java,"bd_haz_v4").build()
        val userDao = db.userDao()

        lifecycleScope.launch {
            var user: User? = withContext(Dispatchers.IO) {
                userDao.findByName(username)
            }
            if(user!=null){
                total = user.total!!
                record = user.record!!

                binding.textView26.setText("Total: "+total)
                binding.textView27.setText("Record: "+record)
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
            if(!empezar) {

                totalPartidas = game.score + total
                if (game.score > record) {
                    record = game.score
                }
                lifecycleScope.launch {
                    withContext(Dispatchers.IO) {
                        userDao.updateRecordAndTotal(username, record, totalPartidas)
                    }
                }
                game.pause()
                val intent = Intent(this, HUBActivity::class.java)
                intent.putExtra("username", username)
                intent.putExtra("avatar", avatar)
                startActivity(intent)
                soundPool.release()
            }
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