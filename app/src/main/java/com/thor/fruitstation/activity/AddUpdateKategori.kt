package com.thor.fruitstation.activity

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.thor.beritahuapp.network.NetworkModule
import com.thor.fruitstation.databinding.ActivityAddUpdateKategoriBinding
import com.thor.fruitstation.model.getdata.DataKategori
import com.udacoding.anggotaapp.Model.action.ResponseAction
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddUpdateKategori : AppCompatActivity() {

    var isUpdate = "false"
    lateinit var binding: ActivityAddUpdateKategoriBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddUpdateKategoriBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val kategori = intent.getParcelableExtra<DataKategori>("kategori")
        isUpdate = intent.getStringExtra("isupdate").toString()

        Log.d("isapdet", kategori?.idKategori.toString())
        binding.btnSimpan.setOnClickListener {
            val namaProduk = binding.inputNamaKategori.text.toString()

            if (isUpdate.equals("true")) {
                updateKategori(kategori?.idKategori ?: 0, namaProduk)
            } else {
                inputKategori(namaProduk)
            }
        }
    }

    private fun updateKategori(id_kategori: Int, namaKategori: String) {
        val input = NetworkModule.service().updateKategori(
            id_kategori ?: 0,
            namaKategori ?: ""
        )
        input.enqueue(object : Callback<ResponseAction> {

            override fun onResponse(
                call: Call<ResponseAction>,
                response: Response<ResponseAction>
            ) {
                Toast.makeText(this@AddUpdateKategori, "Data berhasil disimpan", Toast.LENGTH_SHORT)
                    .show()
                finish()
            }

            override fun onFailure(call: Call<ResponseAction>, t: Throwable) {
                Toast.makeText(this@AddUpdateKategori, t.message, Toast.LENGTH_SHORT).show()
            }

        })
    }

    private fun inputKategori(namaProduk: String?) {
        val input = NetworkModule.service().insertKategori(
            namaProduk ?: ""
        )
        input.enqueue(object : Callback<ResponseAction> {

            override fun onResponse(
                call: Call<ResponseAction>,
                response: Response<ResponseAction>
            ) {
                Toast.makeText(this@AddUpdateKategori, "Data berhasil disimpan", Toast.LENGTH_SHORT)
                    .show()
                finish()
            }

            override fun onFailure(call: Call<ResponseAction>, t: Throwable) {
                Toast.makeText(this@AddUpdateKategori, t.message, Toast.LENGTH_SHORT).show()
            }

        })
    }


}