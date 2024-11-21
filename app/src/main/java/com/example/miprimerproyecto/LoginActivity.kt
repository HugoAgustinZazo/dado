package com.example.miprimerproyecto

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.example.miprimerproyecto.databinding.ActivityLoginBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val db = Room.databaseBuilder(applicationContext,UsuariosDataBase::class.java,"bd_haz").build()
        val userDao = db.userDao()
        binding.button.setOnClickListener{
            val username = binding.editTextText3.text.toString()
            val password = binding.editTextTextPassword2.text.toString()
            lifecycleScope.launch {
                var usuario : User? =  withContext(Dispatchers.IO){
                     userDao.findByName(username)
                }
                binding.mensajeuser.setText(usuario.toString())
                if(usuario!=null){
                    binding.mensajeuser.setText(usuario.toString())
                    if(password.equals(usuario!!.password)){
                        val intent = Intent(this@LoginActivity, HUBActivity::class.java)
                        startActivity(intent)
                    }else{

                    }
                }
            }
        }
        binding.button4.setOnClickListener{
            val intent = Intent(this@LoginActivity, RegistroActivity::class.java)
            startActivity(intent)
        }





    }
}