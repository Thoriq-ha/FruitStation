package com.thor.fruitstation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.thor.fruitstation.databinding.ActivityDaftarAkunBinding

class DaftarAkun : AppCompatActivity() {

    lateinit var binding: ActivityDaftarAkunBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDaftarAkunBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnDaftar.setOnClickListener(){

        }

    }


}