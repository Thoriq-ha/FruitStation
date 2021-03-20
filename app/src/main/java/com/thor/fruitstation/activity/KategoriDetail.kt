package com.thor.fruitstation.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.thor.beritahuapp.network.NetworkModule
import com.thor.fruitstation.adapter.DetailKategoriAdapter
import com.thor.fruitstation.databinding.ActivityKategoriDetailBinding
import com.thor.fruitstation.model.getdata.DataKategori
import com.udacoding.anggotaapp.Model.getdata.ResponseGetData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class KategoriDetail() : AppCompatActivity() {

    lateinit var binding: ActivityKategoriDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKategoriDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val kategori = intent.getParcelableExtra<DataKategori>("kategori")
        getDetailKategori(NetworkModule.service().getDataByKategori(kategori?.idKategori ?: 0))
    }

    private fun getDetailKategori(res: Call<ResponseGetData>) {
        res.enqueue(object : Callback<ResponseGetData> {
            override fun onResponse(call: Call<ResponseGetData>, response: Response<ResponseGetData>) {
                if (response.isSuccessful) {
                    Log.d("response succes", response.message())
                    val data = response.body()?.data
                    Log.d("datadetail", data?.size?.toString()!!)
                    if (data?.size ?: 0 > 0) {
                        binding.rvKategori.adapter = DetailKategoriAdapter(data, this@KategoriDetail)
                    }
                }
            }

            override fun onFailure(call: Call<ResponseGetData>, t: Throwable) {
                Log.d("koneksi on failure", t.message.toString())
            }

        })
    }

    override fun onResume() {
        super.onResume()
//        getDetailKategori(NetworkModule.service().getDataByKategori(idKategori))
    }
}