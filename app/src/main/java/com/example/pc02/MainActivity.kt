package com.example.pc02

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val btnRegistro: Button = findViewById(R.id.btnRegistro)
        val btnLogin: Button = findViewById(R.id.btnIngresar)

        val etCorreo: EditText = findViewById(R.id.etcorreo)
        val etpassword: EditText = findViewById(R.id.etclave)
        val db = FirebaseAuth.getInstance()


        btnRegistro.setOnClickListener{
            startActivity(Intent(this, RegistroActivity::class.java))
        }

        btnLogin.setOnClickListener{
            val correo = etCorreo.text.toString()
            val clave = etpassword.text.toString()

            db.signInWithEmailAndPassword(correo, clave)
                .addOnCompleteListener(this){task ->
                    if(task.isSuccessful){
                        Toast.makeText(this, "Inicio satisfactorio", Toast.LENGTH_LONG).show()
                        startActivity(Intent(this, MainActivity::class.java))
                    }else{
                        Toast.makeText(this, "Correo y/o clave incorrecta", Toast.LENGTH_LONG).show()
                    }

                }


        }



    }
}