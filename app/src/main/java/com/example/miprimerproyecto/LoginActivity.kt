package com.example.miprimerproyecto

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.example.miprimerproyecto.databinding.ActivityLoginBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    private lateinit var userPreferences: UserPreferences
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
        userPreferences = UserPreferences(applicationContext)
        observeData()
        val db = Room.databaseBuilder(applicationContext, UsuariosDataBase::class.java, "bd_haz_v2").build()
        val userDao = db.userDao()
        binding.textView6.textSize = 20F
        binding.textView7.textSize = 20F

        binding.button.setOnClickListener {
            val username = binding.editTextText3.text.toString()
            val password = binding.editTextTextPassword2.text.toString()
            lifecycleScope.launch {
                var usuario: User? = withContext(Dispatchers.IO) {
                    userDao.findByName(username)
                }
                lifecycleScope.launch {
                    if (binding.checkBox2.isChecked) {
                        guardarCredenciales(username, password, true)
                    }
                }
                binding.mensajeuser.setText("")
                binding.textView8.setText("")
                if (usuario != null) {
                    if (password.equals(usuario!!.password)) {
                        val intent = Intent(this@LoginActivity, HUBActivity::class.java)
                        intent.putExtra("username", username)
                        intent.putExtra("avatar",usuario.avatar)
                        startActivity(intent)
                    } else {
                        binding.textView8.setText("La contraseña no coincide con el usuario")

                    }
                } else {
                    binding.mensajeuser.setText("El usuario introducido no existe. Registrese primero")
                }
            }
        }

        binding.checkBox2.setOnCheckedChangeListener { _, isChecked ->
            lifecycleScope.launch {
                if (!isChecked) {
                    limpiarCredenciales()
                } else {
                    guardarCredenciales(
                        binding.editTextText3.text.toString(),
                        binding.editTextTextPassword2.text.toString(),
                        true
                    )
                }
            }
        }
        binding.button4.setOnClickListener {
            val intent = Intent(this@LoginActivity, RegistroActivity::class.java)
            startActivity(intent)
        }
    }
    private suspend fun guardarCredenciales(username: String, password: String, isChecked: Boolean) {
        withContext(Dispatchers.IO) {
            userPreferences.saveUserData(username, password, isChecked)
        }
    }

    private fun observeData() {
        lifecycleScope.launch {
            userPreferences.userData.collect { userData ->
                binding.editTextText3.setText(userData.username)
                binding.editTextTextPassword2.setText(userData.password)
                binding.checkBox2.isChecked = userData.isChecked
            }
        }
    }
    private suspend fun limpiarCredenciales() {
        withContext(Dispatchers.IO) {
            userPreferences.limpiraDatos()
        }
    }
}
