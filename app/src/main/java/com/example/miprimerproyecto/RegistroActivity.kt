package com.example.miprimerproyecto

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.miprimerproyecto.databinding.ActivityLoginBinding
import com.example.miprimerproyecto.databinding.ActivityRegistroBinding
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
        val usuariosDataBase = UsuariosDataBase.getDatabase(this@RegistroActivity).UsuarioDao()
        binding.fechanac.setOnClickListener {
            datePicker()
        }

        binding.registrarse.setOnClickListener {
            var contadorErrores: Int = 0
            val username = binding.editTextText2.text.toString()
            val password = binding.editTextTextPassword.text.toString()
            if (username.isNotEmpty()) {
                if (username.length < 4 || username.length > 10) {
                    binding.mensajeerrorusername.setText("El usuario debe tener entre 4 y 10 caracteres")
                    contadorErrores++
                } else {
                    //val Usuario = usuariosDataBase.findByName(username.toString())
                    /*if (Usuario != null) {
                        binding.mensajeerrorusername.setText("El usuario introducido ya existe. Introduzca uno diferente")
                        contadorErrores++
                    }

                     */
                }
            } else {
                binding.mensajeerrorusername.setText("Deberas introducir un nombre de usuario")
                contadorErrores++
            }
            if(!comparaEdad(añonac,mesnac,dianac)){
                binding.mensajeerrorfechanac.setText("Eres menor de 16 años no puedes registrarte")
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
                val intent = Intent(this@RegistroActivity, LoginActivity::class.java)
                startActivity(intent)
            }


        }


    }





    private fun datePicker(){

        val year = 2000
        val month = 0
        val day = 1

        val datePickerDialog = DatePickerDialog(
            this,
            { view, year1, monthOfYear, dayOfMonth ->
                val dateChoice = (dayOfMonth.toString() + "-" + (monthOfYear) + "-" + year1)
                añonac=year1
                mesnac = monthOfYear
                dianac = dayOfMonth
                binding.fechanac.setText(dateChoice)

            }, year, month, day

        )
        datePickerDialog.show()
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

        if(LocalDate.now().year>=(año+16)){
            if(LocalDate.now().monthValue>=mes){
                if(LocalDate.now().dayOfMonth>=dia){
                    return true
                }else{
                    return false
                }
            }else{
                return false
            }
        }else{
            return false
        }
        return false
    }
}
