package com.example.miprimerproyecto

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.miprimerproyecto.databinding.ActivityLoginBinding
import com.example.miprimerproyecto.databinding.ActivityRegistroBinding
import java.time.LocalDate

class RegistroActivity : AppCompatActivity() {
    lateinit var binding: ActivityRegistroBinding
    //val usuariosDatabase = Aplicacion.usuariosDataBase
    var contadorErrores: Int = 0
    var añonac :Int = 0
    var mesnac :Int = 0
    var dianac :Int = 0

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
        binding.fechanac.setOnClickListener {
            datePicker()
        }

        binding.registrarse.setOnClickListener {

            val username = binding.editTextText2.text.toString()
            val password = binding.editTextTextPassword.text
            if(username.isNotEmpty()){
            if (username.length < 4 || username.length > 10) {
                binding.mensajeerrorusername.setText("El usuario debe tener entre 4 y 10 caracteres")
                contadorErrores++
            }
            }else{
                binding.mensajeerrorusername.setText("Deberas introducir un nombre de usuario")
            }
            if (!binding.checkBox.isChecked) {
                binding.mensajeerrorpoliticas.setText("Deberas aceptar las politicas y condiciones de privacidad")
                contadorErrores++
            }
            binding.mensajeerrorfechanac.setText(""+añonac+""+mesnac+""+dianac)
           /*
            val Usuario = usuariosDatabase.UsuarioDao().findByName(username.toString())
            if (Usuario != null) {
                binding.mensajeerrorusername.setText("El usuario introducido ya existe. Introduzca uno diferente")
            } else {
                //usuariosDatabase.UsuarioDao().insert(Usuario(username, password,))
            }

            */

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

    private fun comparaEdad(año: Int,mes: Int,dia: Int){
    }
}
