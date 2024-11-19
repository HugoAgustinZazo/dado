package com.example.miprimerproyecto

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.miprimerproyecto.databinding.ActivityLoginBinding
import com.example.miprimerproyecto.databinding.ActivityRegistroBinding

class RegistroActivity : AppCompatActivity() {
    lateinit var binding: ActivityRegistroBinding
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
        binding.fechanac.setOnClickListener{
            datePicker()
        }

        binding.registrarse.setOnClickListener{
            val username = binding.tvusername.text



        }
    }
    private fun datePicker(){

        val year = 2000
        val month = 0
        val day = 1

        val datePickerDialog = DatePickerDialog(
            this,
            { view, year1, monthOfYear, dayOfMonth -> val dateChoice = (dayOfMonth.toString() + "-" + (monthOfYear + 1) + "-" + year1)
                binding.fechanac.setText(dateChoice)

            }, year, month, day
        )
        datePickerDialog.show()
    }
}
