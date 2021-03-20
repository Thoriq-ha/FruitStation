package com.thor.fruitstation.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.thor.beritahuapp.network.NetworkModule
import com.thor.fruitstation.adapter.AllItemAdapter
import com.thor.fruitstation.adapter.KategoriAdapter
import com.thor.fruitstation.databinding.ActivityHomeBinding
import com.thor.fruitstation.model.getdata.DataKategori
import com.thor.fruitstation.model.getdata.ResponseGetKategori
import com.udacoding.anggotaapp.Model.action.ResponseAction
import com.udacoding.anggotaapp.Model.getdata.DataItem
import com.udacoding.anggotaapp.Model.getdata.ResponseGetData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Home : AppCompatActivity() {

    lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getAllItem()
        getKategori()

        binding.btnLetsell.setOnClickListener {
            val intent = Intent(this, LetSell::class.java)
            startActivity(intent)
        }
        binding.txtAddCategory.setOnClickListener {
            val intent = Intent(this, AddUpdateKategori::class.java)
            startActivity(intent)
        }
    }

    private fun getKategori() {
        NetworkModule.service().getKategori().enqueue(object : Callback<ResponseGetKategori> {
            override fun onResponse(
                call: Call<ResponseGetKategori>,
                response: Response<ResponseGetKategori>
            ) {
                val kategori = response.body()?.data
                val adapter =
                    KategoriAdapter(kategori, this@Home, object : KategoriAdapter.OnClickListener {
                        override fun hapus(item: DataKategori?) {
                            Log.d("hapus", "Tes Hapus Gan")
                            hapusKategori(item?.idKategori)
                        }
                    })
                if (kategori?.size ?: 0 > 0) {
                    binding.rvKategori.adapter = adapter
                }
            }

            override fun onFailure(call: Call<ResponseGetKategori>, t: Throwable) {
                Toast.makeText(applicationContext, t.message, Toast.LENGTH_SHORT).show()
            }
        })

    }

    private fun getAllItem() {
        NetworkModule.service().getMenu().enqueue(object : Callback<ResponseGetData> {
            override fun onResponse(
                call: Call<ResponseGetData>,
                response: Response<ResponseGetData>
            ) {
                if (response.isSuccessful) {
                    binding.progressBar.visibility = View.GONE
                    Log.d("response succes", response.message())
                    val data = response.body()?.data
                    val adapter =
                        AllItemAdapter(data, this@Home, object : AllItemAdapter.OnClickListener {
                            override fun hapus(item: DataItem?) {
                                Log.d("hapus", "Tes Hapus Gan")
                                hapusData(item?.id)
                            }
                        })
                    if (data?.size ?: 0 > 0) {
                        binding.rvRecomendedCombo.adapter = adapter
                    }
                }
            }

            override fun onFailure(call: Call<ResponseGetData>, t: Throwable) {
                binding.progressBar.visibility = View.GONE
                Log.d("koneksi on failure", t.message.toString())
            }

        })
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
                getAllItem()
            }

            override fun onFailure(call: Call<ResponseAction>, t: Throwable) {
                Toast.makeText(applicationContext, t.message, Toast.LENGTH_SHORT).show()
            }
        })

    }

    private fun hapusKategori(id: Int?) {

        val hapus = NetworkModule.service().deleteKategori(id ?: 0)
        hapus.enqueue(object : Callback<ResponseAction> {

            override fun onResponse(
                call: Call<ResponseAction>,
                response: Response<ResponseAction>
            ) {
                Toast.makeText(applicationContext, "Kategori Berhasil dihapus", Toast.LENGTH_SHORT)
                    .show()
                getKategori()
            }

            override fun onFailure(call: Call<ResponseAction>, t: Throwable) {
                Toast.makeText(applicationContext, t.message, Toast.LENGTH_SHORT).show()
            }
        })

    }


    override fun onResume() {
        super.onResume()
        getKategori()
        getAllItem()
    }
}