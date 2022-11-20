package com.example.pc02

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.pc02.model.UsuarioModel
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*

class RegistroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        val txtRegCorreo: EditText = findViewById(R.id.txtregcorreo)
        val txtRegNombre: EditText = findViewById(R.id.txtregnombre)
        val txtRegClave: EditText = findViewById(R.id.txtregclave)
        val btnRegUsuario: Button = findViewById(R.id.btnRegistrarCuenta)
        val db = FirebaseFirestore.getInstance()

        btnRegUsuario.setOnClickListener{
            val regCorreo = txtRegCorreo.text.toString()
            val regNombre = txtRegNombre.text.toString()
            val regClave = txtRegClave.text.toString()

            val nuevoUsuario = UsuarioModel(regNombre, regCorreo, regClave)
            val id: UUID = UUID.randomUUID()

            db.collection("usuarios")
                .document(id.toString())
                .set(nuevoUsuario)
                .addOnSuccessListener {
                    startActivity(Intent(this, RegistroActivity::class.java))
                    Toast.makeText(this, "Se registró correctamente", Toast.LENGTH_LONG).show()
                }.addOnFailureListener{
                    Toast.makeText(this, "Ocurrió un error al registra cuenta", Toast.LENGTH_LONG).show()
                }

        }

    }


}