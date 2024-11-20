package com.example.miprimerproyecto

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
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
        binding.button.setOnClickListener{
            val username = binding.editTextText3.text.toString()
            val password = binding.editTextTextPassword2.text.toString()
            var usuario : Usuario? = null
            lifecycleScope.launch {
                withContext(Dispatchers.IO){
                    val usuariosDataBase = UsuariosDataBase.getDatabase(this@LoginActivity).UsuarioDao()
                    usuario = usuariosDataBase.findByName(username)
                }
            }
            binding.mensajeuser.setText(usuario.toString())
            if(usuario!=null){
                if(password.equals(usuario!!.password)){
                    val intent = Intent(this@LoginActivity, HUBActivity::class.java)
                    startActivity(intent)
                }else{

                }
            }
        }
        binding.button4.setOnClickListener{
            val intent = Intent(this@LoginActivity, RegistroActivity::class.java)
            startActivity(intent)
        }





    }
}