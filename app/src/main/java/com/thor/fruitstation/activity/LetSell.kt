package com.thor.fruitstation.activity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.thor.beritahuapp.network.NetworkModule
import com.thor.fruitstation.databinding.ActivityLetSellBinding
import com.udacoding.anggotaapp.Model.action.ResponseAction
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LetSell : AppCompatActivity() {

    lateinit var binding: ActivityLetSellBinding

    var isUpdate = "false"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLetSellBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val id = intent.getStringExtra("id")
         isUpdate = intent.getStringExtra("isupdate").toString()

        binding.btnSell.setOnClickListener {
            val namaProduk = binding.inputNamaItem.text.toString()
            val harga = binding.inputHarga.text.toString().toInt()
            val deskripsi = binding.inputDeskripsi.text.toString()
            val img_url = binding.inputGambar.text.toString()
            val kategori = binding.inputKategori.text.toString().toInt()

            if (isUpdate.equals("true")) {
                updateItem(id.toString().toInt(), namaProduk, harga, deskripsi, img_url, kategori)
            } else {
                inputItem(namaProduk, harga, deskripsi, img_url, kategori)
            }

        }
    }

    private fun updateItem(
        id: Int?,
        namaProduk: String?,
        harga: Int?,
        deskripsi: String?,
        img_url: String?,
        kategori: Int?
    ) {
        val update = NetworkModule.service().updateData(
            id ?: 0,
            namaProduk ?: "",
            harga ?: 0,
            deskripsi ?: "",
            img_url ?: "",
            kategori ?: 0
        )
        update.enqueue(object : Callback<ResponseAction> {

            override fun onResponse(
                call: Call<ResponseAction>,
                response: Response<ResponseAction>
            ) {
                Toast.makeText(this@LetSell, "Data berhasil disimpan", Toast.LENGTH_SHORT).show()
                finish()
            }

            override fun onFailure(call: Call<ResponseAction>, t: Throwable) {
                Toast.makeText(this@LetSell, t.message, Toast.LENGTH_SHORT).show()
            }

        })

    }

    private fun inputItem(
        namaProduk: String?,
        harga: Int?,
        deskripsi: String?,
        img_url: String?,
        kategori: Int?
    ) {
        val input = NetworkModule.service().insertItem(
            namaProduk ?: "",
            harga ?: 0,
            deskripsi ?: "",
            img_url ?: "",
            kategori ?: 0
        )
        input.enqueue(object : Callback<ResponseAction> {

            override fun onResponse(
                call: Call<ResponseAction>,
                response: Response<ResponseAction>
            ) {
                Toast.makeText(this@LetSell, response.message(), Toast.LENGTH_SHORT).show()
//                Toast.makeText(this@LetSell, "Data berhasil disimpan", Toast.LENGTH_SHORT).show()
                finish()
            }

            override fun onFailure(call: Call<ResponseAction>, t: Throwable) {
                Toast.makeText(this@LetSell, t.message, Toast.LENGTH_SHORT).show()
            }

        })

    }
}