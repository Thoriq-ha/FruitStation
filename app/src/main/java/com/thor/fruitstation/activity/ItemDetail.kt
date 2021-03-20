package com.thor.fruitstation.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.thor.beritahuapp.network.NetworkModule
import com.thor.fruitstation.databinding.ActivityItemDetailBinding
import com.udacoding.anggotaapp.Model.action.ResponseAction
import com.udacoding.anggotaapp.Model.getdata.DataItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ItemDetail : AppCompatActivity() {

    lateinit var binding: ActivityItemDetailBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityItemDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val getData = intent.getParcelableExtra<DataItem>("dataitem")
        val id = getData?.id
        showDataItemDetail(getData ?: null)

        binding.btnEditProduk.setOnClickListener {
            val intent = Intent(this, LetSell::class.java)
            intent.putExtra("isupdate", true)
            intent.putExtra("id", id)

            startActivity(intent)
        }

        binding.btnAddToBasket.setOnClickListener() {

        }


        binding.btnHapus.setOnClickListener {
            hapusData(id)
        }

        binding.btnAddToBasket

    }

    private fun showDataItemDetail(dataItem: DataItem?) {
        val namaMenu = dataItem?.namaMenu
        val hargaa = dataItem?.harga
        val deskripsi = dataItem?.deskripsi
        val imgUrl = dataItem?.imgUrl


        Glide.with(this).load(imgUrl).into(binding.imgItemDetail)
        binding.txtNamaItemDetail.text = namaMenu
        binding.txtDeskripsiItem.text = deskripsi

    }


    private fun hapusData(id: String?) {

        val hapus = NetworkModule.service().deleteData(id ?: "")
        hapus.enqueue(object : Callback<ResponseAction> {

            override fun onResponse(
                call: Call<ResponseAction>,
                response: Response<ResponseAction>
            ) {

                Toast.makeText(applicationContext, "Data Berhasil dihapus", Toast.LENGTH_SHORT)
                    .show()

            }

            override fun onFailure(call: Call<ResponseAction>, t: Throwable) {

                Toast.makeText(applicationContext, t.message, Toast.LENGTH_SHORT).show()

            }

        })

    }

    override fun onResume() {
        super.onResume()
        val getData = intent.getParcelableExtra<DataItem>("dataitem")
        showDataItemDetail(getData)
    }

}