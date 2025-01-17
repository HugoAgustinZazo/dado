package com.example.miprimerproyecto.firebaseapp


import android.content.ContentValues.TAG
import android.os.Binder
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.miprimerproyecto.R
import com.example.miprimerproyecto.databinding.ActivityFbSinAutBinding
import com.example.miprimerproyecto.firebaseapp.MainActivity.Companion
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import com.squareup.picasso.Picasso

class FbSinAut : AppCompatActivity() {
    lateinit var binding: ActivityFbSinAutBinding
    private lateinit var auth: FirebaseAuth
    private val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFbSinAutBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(R.layout.activity_fb_sin_aut)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val username = intent.getStringExtra("username")
        val avatar = intent.getStringExtra("avatar")
        Picasso.get().load(avatar).into(binding.imageView20)
        binding.textView17.setText(binding.textView17.text.toString()+" "+username)

        auth = Firebase.auth

        auth.signInAnonymously()
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInAnonymously:success")
                    val user = auth.currentUser

                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInAnonymously:failure", task.exception)
                    Toast.makeText(
                        baseContext,
                        "Authentication failed.",
                        Toast.LENGTH_SHORT,
                    ).show()
                    updateUI(null)
                }
            }



        binding.button10.setOnClickListener() { e ->
            binding.button12.visibility = View.GONE
            binding.textView18.visibility = View.VISIBLE
            binding.button10.visibility = View.GONE

            db.collection("alumnoshaz").get()
                .addOnSuccessListener { result ->val stringBuilder = StringBuilder()
                    for (document in result) {
                        stringBuilder.append(
                            "***********************************************\n" +
                                    "    Nombre: ${document.data["nombre"]}      Apellido: ${document.data["apellido"]}\n" +
                                    "    Nia: ${document.data["nia"]}    Nif: ${document.data["nif"]}    Media Exp: ${document.data["mediaexpediente"]}\n" +
                                    "***********************************************\n"
                        )
                    }
                    binding.textView18.text = stringBuilder.toString()
                }
                .addOnFailureListener { e ->
                    Log.w(TAG, "Error adding document", e)
                    Log.d("Hugo",e.toString())
                }

        }
        binding.button11.setOnClickListener() {
            binding.button12.visibility = View.VISIBLE
            binding.button10.visibility = View.VISIBLE
            binding.textView18.visibility = View.GONE
            binding.bnia.visibility = View.GONE
            binding.bnif.visibility = View.GONE
            binding.editTextText4.visibility = View.GONE
            binding.button18.visibility = View.GONE
            binding.textView18.setText("")
            binding.editTextText4.setText("")


        }
        binding.button12.setOnClickListener() {
            binding.bnia.visibility = View.VISIBLE
            binding.bnif.visibility = View.VISIBLE
            binding.button12.visibility = View.GONE
            binding.button10.visibility = View.GONE

            binding.bnia.setOnClickListener(){
                binding.bnia.visibility = View.GONE
                binding.bnif.visibility = View.GONE

                binding.editTextText4.visibility = View.VISIBLE
                binding.button18.visibility = View.VISIBLE

                binding.button18.setOnClickListener(){
                    if(binding.editTextText4.text==null){
                        binding.textView18.setText("No has introducido ningun NIA")
                    }else{
                        getAlumnosNia(binding.editTextText4.text.toString())
                        binding.textView18.visibility = View.VISIBLE
                    }
                }
            }
            binding.bnif.setOnClickListener(){

                binding.bnia.visibility = View.GONE
                binding.bnif.visibility = View.GONE

                binding.editTextText4.visibility = View.VISIBLE
                binding.button18.visibility = View.VISIBLE

                binding.button18.setOnClickListener(){
                    if(binding.editTextText4.text.toString().equals("")){
                        binding.textView18.setText("No has introducido ningun NIF")

                    }else{
                        getAlumnosNif(binding.editTextText4.text.toString())
                        binding.textView18.visibility = View.VISIBLE
                    }
                }
            }
        }
    }private fun getAlumnosNia(nia: String){

        db.collection("alumnoshaz").whereEqualTo("nia",nia).get()
            .addOnSuccessListener { result ->
                if (!result.isEmpty) {
                for (document in result) {
                    binding.textView18.setText(
                        "***********************************************\n" +
                                "    Nombre: ${document.data["nombre"]}\n" +
                                "    Apellido: ${document.data["apellido"]}\n" +
                                "    Nia: ${document.data["nia"]}\n" +
                                "    Nif: ${document.data["nif"]}\n" +
                                "    Media Exp: ${document.data["mediaexpediente"]}\n" +
                                "***********************************************\n"
                    )
                }
            } else {
                binding.textView18.setText("No se encontró ningún alumno con NIA $nia")
            }}
            .addOnFailureListener { e ->
                Log.w(TAG, "Error adding document", e)
                Log.d("Hugo",e.toString())
            }
    }
    private fun getAlumnosNif(nif: String){

        db.collection("alumnoshaz").whereEqualTo("nif",nif).get()
            .addOnSuccessListener { result ->
                if (!result.isEmpty) {
                for (document in result) {
                    binding.textView18.setText(
                        "***********************************************\n" +
                                "    Nombre: ${document.data["nombre"]}\n" +
                                "    Apellido: ${document.data["apellido"]}\n" +
                                "    Nia: ${document.data["nia"]}\n" +
                                "    Nif: ${document.data["nif"]}\n" +
                                "    Media Exp: ${document.data["mediaexpediente"]}\n" +
                                "***********************************************\n"
                    )
                }
            } else {
                binding.textView18.setText("No se encontró ningún alumno con NIF  $nif")
            }}
            .addOnFailureListener { e ->
                Log.w(TAG, "Error adding document", e)
                Log.d("Hugo",e.toString())
            }
    }
    private fun updateUI(user: FirebaseUser?) {
    }

}




