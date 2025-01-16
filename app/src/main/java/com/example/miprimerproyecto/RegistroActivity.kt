package com.example.miprimerproyecto

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.example.miprimerproyecto.databinding.ActivityLoginBinding
import com.example.miprimerproyecto.databinding.ActivityRegistroBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.LocalDate
import java.time.Year

class RegistroActivity : AppCompatActivity() {
    lateinit var binding: ActivityRegistroBinding
    var añonac :Int = 0
    var mesnac :Int = 0
    var dianac :Int = 0

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistroBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(R.layout.activity_registro)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val db = Room.databaseBuilder(applicationContext,UsuariosDataBase::class.java,"bd_haz").build()
        val userDao = db.userDao()
        lifecycleScope.launch {
            withContext(Dispatchers.IO) {
                userDao.getAll()
            }
        }
        binding.tvregistromensaje.textSize = 25F
        binding.fechanac.setOnClickListener {
            datePicker()
        }

        binding.registrarse.setOnClickListener {
            binding.mensajeerrorfechanac.setText("")
            binding.mensajeerrorusername.setText("")
            binding.mensajeerrorpoliticas.setText("")
            binding.mensajeerrorcontraseA.setText("")
            var contadorErrores: Int = 0
            val username = binding.editTextText2.text.toString()
            val password = binding.editTextTextPassword.text.toString()
            if (username.isNotEmpty()) {
                if (username.length < 4 || username.length > 10) {
                    binding.mensajeerrorusername.setText("El usuario debe tener entre 4 y 10 caracteres")
                    contadorErrores++
                } else {
                }
            } else {
                binding.mensajeerrorusername.setText("Deberas introducir un nombre de usuario")
                contadorErrores++
            }
            if(!comprobarHayFecha()){
                binding.mensajeerrorfechanac.setText("Debe introduci una fecha de nacimiento")
                contadorErrores++
            }
            if(!comparaEdad(añonac,mesnac,dianac)) {
                binding.mensajeerrorfechanac.setText("Eres menor de 16 años no puedes registrarte")
                contadorErrores++
            }

            if (password.isNotEmpty()) {
                if (!comprobarNumeroContraseña(password) || (username.length < 4 || username.length > 10)) {
                    binding.mensajeerrorcontraseA.setText("La contraseña tiene que tener entre 4 y 10 caracteres y al menos un número")
                    contadorErrores++
                }
            }
                if (binding.checkBox.isChecked) {
                    binding.mensajeerrorpoliticas.setText("")
                }else{
                    binding.mensajeerrorpoliticas.setText("Deberas aceptar las politicas y condiciones de privacidad")
                    contadorErrores++
                }

            if(contadorErrores==0){
                lifecycleScope.launch {
                    var user: User? = withContext(Dispatchers.IO) {
                        userDao.findByName(username)
                    }
                        if(user==null){
                            var fecha: String = "" + dianac + "/" + mesnac + "/" + añonac
                            val usuario = User(username = username, password = password, fechanac = fecha)
                            withContext(Dispatchers.IO) {
                                userDao.insertUser(usuario)
                            }
                            val intent = Intent(this@RegistroActivity, LoginActivity::class.java)
                            startActivity(intent)
                            Toast.makeText(
                                this@RegistroActivity,
                                "Usuario registrado: "+username,
                                Toast.LENGTH_SHORT
                            ).show()

                        }else {

                                binding.mensajeerrorusername.text = "El usuario ya existe introduce uno nuevo"
                        }

                }
            }
        }
    }
    @SuppressLint("NewApi")
    private fun datePicker(){

        val year = LocalDate.now().year
        val month = LocalDate.now().monthValue
        val day = LocalDate.now().dayOfMonth

        val datePickerDialog = DatePickerDialog(
            this,
            { view, year1, monthOfYear, dayOfMonth ->
                val dateChoice = (dayOfMonth.toString() + "-" + (monthOfYear+1) + "-" + year1)
                añonac=year1
                mesnac = (monthOfYear+1)
                dianac = dayOfMonth
                binding.fechanac.setText(dateChoice)

            }, year, month, day

        )
        datePickerDialog.show()
    }
    private fun comprobarHayFecha(): Boolean{
        for(i in 0..9){
            if(binding.fechanac.text.contains(i.toString())){
                return true
            }
        }
        return false
    }

    private fun comprobarNumeroContraseña(password: String): Boolean {
        for(i in 0..9){
            if(password.contains(i.toString())){
                return true
            }
        }
        return false
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun comparaEdad(año: Int, mes: Int, dia: Int): Boolean{
    var añomayor = false
    var añoMayorIgual = ""

        if(LocalDate.now().year>=(año+16)){
            if(LocalDate.now().year==(año+16)){
                añoMayorIgual = "igual"
            }else{
                añoMayorIgual = "mayor"
            }
            añomayor = true
            if(LocalDate.now().monthValue>=mes||añomayor){
                if(añoMayorIgual.equals("igual")&&LocalDate.now().monthValue<mes){
                    return false
                }else {
                    if (LocalDate.now().dayOfMonth >= dia || añomayor) {
                        if (añoMayorIgual.equals("igual")&&LocalDate.now().dayOfMonth < dia) {
                            return false
                        } else {
                            return true
                        }
                    } else {
                        return false
                    }
                }
            }else{
                return false
            }
        }else
            return false

    }
}
