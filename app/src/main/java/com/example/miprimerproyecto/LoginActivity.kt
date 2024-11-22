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
        binding.textView6.textSize = 20F
        binding.textView7.textSize = 20F

        binding.button.setOnClickListener{
            val username = binding.editTextText3.text.toString()
            val password = binding.editTextTextPassword2.text.toString()
            lifecycleScope.launch {
                var usuario : User? =  withContext(Dispatchers.IO){
                     userDao.findByName(username)
                }
                binding.mensajeuser.setText("")
                binding.textView8.setText("")
                if(usuario!=null){
                    if(password.equals(usuario!!.password)){
                        val intent = Intent(this@LoginActivity, HUBActivity::class.java)
                        intent.putExtra("username",username)
                        startActivity(intent)

                    }else{
                        binding.textView8.setText("La contraseña no coincide con el usuario")
                    }
                }else {
                    binding.mensajeuser.setText("El usuario introducido no existe. Registrese primero")
                }
            }
        }
        binding.button4.setOnClickListener{
            val intent = Intent(this@LoginActivity, RegistroActivity::class.java)
            startActivity(intent)
        }





    }
}