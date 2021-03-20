package com.thor.fruitstation.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.thor.fruitstation.databinding.ActivityLoginBinding

class Login : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener() {
            val userName = binding.inputUsername.text.toString()
            val password = binding.inputPassword.text.toString()
            Log.d("ceklogin", userName+password)

            if (userName.equals("thor") &&
                password.equals("thor")
            ) {
                val intent = Intent(this, Home::class.java)
                startActivity(intent)
                finish()
            } else if (userName.equals("") || password.equals("")) {
                Toast.makeText(
                    this,
                    "Username dan Password tidak boleh kosong",
                    android.widget.Toast.LENGTH_SHORT
                ).show()
            } else {
                Toast.makeText(this, "Inputan salah", Toast.LENGTH_SHORT).show()
            }
        }

        binding.txtDaftarAkun.setOnClickListener(){
            val intent = Intent(this, DaftarAkun::class.java)
            startActivity(intent)
        }

    }
}